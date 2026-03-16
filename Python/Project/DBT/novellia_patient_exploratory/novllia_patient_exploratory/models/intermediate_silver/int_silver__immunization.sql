{{ config(materialized= 'incremental',
          schema = 'intermediate',
          unique_key =['immunization_id','immunization_code'])}}

SELECT
  immunization_id,
  inserted_at,
  updated_at,
  immunization_status,
  immunization_occured_at,
  immunization_primary_source_ind,
  immunization_site,
  patient_id,
  encounter_id,
  immunization_type,
  immunization_code

  FROM
    {{ ref('stg_bronze__immunization_events') }}

WHERE 
  latest_immunization_record = 1

{% if is_incremental() %}

  AND updated_at > (SELECT COALESCE(MAX(updated_at), '2000-01-01' )FROM {{this}}) 

{% endif %}
