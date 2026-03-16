{{ config(materialized= 'incremental',
          schema = 'staging')}}
WITH CTE AS (
SELECT
  pe.data->>'id' AS patient_id,
  pe.inserted_at,
  pe.updated_at,
  coding->>'display' AS id_name,
  id->>'value' AS id_value
    
FROM 
  {{source('individuals','patient_events')}} pe
CROSS JOIN 
  jsonb_array_elements(pe.data->'identifier') id
LEFT JOIN LATERAL 
  jsonb_array_elements(id->'type'->'coding') AS coding ON TRUE




{% if is_incremental() %}

WHERE pe.updated_at > (SELECT COALESCE(MAX(updated_at), '2000-01-01' )FROM {{this}})

{% endif %})

SELECT
  patient_id,
  inserted_at,
  updated_at,
  id_name,
  id_value,
  ROW_NUMBER() OVER(PARTITION BY patient_id ORDER BY updated_at DESC) AS patient_record_count,
  ROW_NUMBER() OVER(PARTITION BY patient_id, id_name  ORDER BY updated_at DESC) AS patient_id_order

FROM
  CTE

WHERE 
  id_name IS NOT NULL
