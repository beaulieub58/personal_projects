{{ config(materialized= 'incremental',
          schema = 'staging')}}

SELECT
  pe.data->>'id' AS patient_id,
  pe.inserted_at,
  pe.updated_at,
  REPLACE(SUBSTRING(id->>'line' FROM '\[(.*?)\]'),'"','') AS patient_address_line,
  id->>'city' AS patient_city,
  id->>'state' AS patient_state,
  id->>'postalCode' AS patient_zip,
  id->>'country' AS patient_country,
  ROW_NUMBER() OVER(PARTITION BY pe.data->>'id' ORDER BY updated_at DESC) AS patient_address_order
    
FROM 
  {{source('individuals','patient_events')}} pe
CROSS JOIN 
  jsonb_array_elements(pe.data->'address') id


{% if is_incremental() %}

WHERE pe.updated_at > (SELECT COALESCE(MAX(updated_at), '2000-01-01' )FROM {{this}})

{% endif %}
