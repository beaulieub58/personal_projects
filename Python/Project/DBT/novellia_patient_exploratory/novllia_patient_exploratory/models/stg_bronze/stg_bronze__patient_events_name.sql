{{ config(materialized= 'incremental',
          schema = 'staging')}}

SELECT
  pe.data->>'id' AS patient_id,
  pe.inserted_at,
  pe.updated_at,
  nd->>'use' AS name_use,
  nd->>'family' AS name_family,
  REPLACE(SUBSTRING(nd->>'given' FROM '\[(.*?)\]'),'"','') AS name_given,
  REPLACE(SUBSTRING(nd->>'prefix' FROM '\[(.*?)\]'),'"','') AS name_prefix,
  ROW_NUMBER() OVER(PARTITION BY pe.data->>'id' ORDER BY updated_at DESC) AS patient_name_order
    
FROM 
  {{source('individuals','patient_events')}} pe
CROSS JOIN 
  jsonb_array_elements(pe.data->'name') nd


{% if is_incremental() %}

WHERE pe.updated_at > (SELECT COALESCE(MAX(updated_at), '2000-01-01' )FROM {{this}})

{% endif %}
