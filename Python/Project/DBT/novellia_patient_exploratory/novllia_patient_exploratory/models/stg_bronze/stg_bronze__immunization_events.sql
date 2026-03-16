{{ config(materialized= 'incremental',
          schema = 'staging')}}

SELECT
  ie.data->>'id' AS immunization_id,
  ie.inserted_at,
  ie.updated_at,
  ie.data->>'status' AS immunization_status,
  DATE(ie.data->>'occurrenceDateTime') AS immunization_occured_at,
  CAST(ie.data->>'primarySource' AS BOOLEAN) immunization_primary_source_ind,
  ie.data->'location'->>'display' AS immunization_site,
  RIGHT(ie.data->'patient'->>'reference', LENGTH(ie.data->'patient'->>'reference') - 8) AS patient_id,
  RIGHT(ie.data->'encounter'->>'reference', LENGTH(ie.data->'encounter'->>'reference') - 10) AS encounter_id,
  ie.data->'vaccineCode'->>'text' AS immunization_type,
  vac->>'code' AS immunization_code,
  ROW_NUMBER() OVER(PARTITION BY ie.data->>'id', vac->>'code' ORDER BY updated_at DESC) AS latest_immunization_record


FROM 
  {{source('medications','immunization_events')}} ie
CROSS JOIN  
  jsonb_array_elements(ie.data->'vaccineCode'->'coding') AS vac
  
{% if is_incremental() %}

WHERE ie.updated_at > (SELECT COALESCE(MAX(updated_at), '2000-01-01' )FROM {{this}})

{% endif %}
