{{ config(materialized= 'incremental',
          schema = 'intermediate',
          unique_key = ['observation_id', 'observation_code', 'observation_name_enhanced'])}}

SELECT
  oe.observation_id,
  oe.inserted_at,
  oe.updated_at,
  oe.observation_status,
  oe.observation_effective_at,
  oe.observation_issued_at,
  oe.patient_id,
  oe.encounter_id,
  oe.observation_code,
  oe.observation_name,
  COALESCE(oec.component_display_name, oe.observation_name) AS observation_name_enhanced,
  COALESCE(oe.default_observation_value_quantity, oec.component_value) AS vital_sign_value,
  COALESCE(oe.default_observation_value_unit, oec.component_unit) AS vital_sign_unit,
  ROW_NUMBER() OVER(PARTITION BY oe.observation_id, oe.observation_code, COALESCE(oec.component_display_name, oe.observation_name)) AS observation_dupe_check
 
FROM
  {{ ref('stg_bronze__observation_events') }} oe
LEFT JOIN
  {{ ref('stg_bronze__observation_events_components')}} oec
  ON oe.observation_id = oec.observation_id

WHERE
  oe.category_code = 'vital-signs'

{% if is_incremental() %}

  AND oe.updated_at > (SELECT COALESCE(MAX(updated_at), '2000-01-01' )FROM {{this}}) 

{% endif %}
