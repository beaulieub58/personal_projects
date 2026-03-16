{{ config(materialized= 'incremental',
          schema = 'staging')}}
SELECT
  oe.data->>'id' AS observation_id,
  oe.inserted_at,
  oe.updated_at,
  oe.data->>'status' AS observation_status,
  DATE(oe.data->>'effectiveDateTime') AS observation_effective_at,
  DATE(oe.data->>'issued') AS observation_issued_at,
  SPLIT_PART(oe.data->'subject'->>'reference','/',-1) AS patient_id,
  SPLIT_PART(oe.data->'encounter'->>'reference','/',-1) AS encounter_id,
  oe.data->'code'->'coding'->0->>'code' AS observation_code,
  oe.data->'code'->'coding'->0->>'display' AS observation_name,
  oe.data->'category'->0->'coding'->0->>'code' AS category_code,
  oe.data->'category'->0->'coding'->0->>'display' AS category_name,
  CAST(oe.data->'valueQuantity'->>'value' AS FLOAT) AS default_observation_value_quantity,
  oe.data->'valueQuantity'->>'unit' AS default_observation_value_unit

FROM
  {{ source('diagnostics','observation_events')}} oe
  

{% if is_incremental() %}

WHERE oe.updated_at > (SELECT COALESCE(MAX(updated_at), '2000-01-01' )FROM {{this}})

{% endif %}
