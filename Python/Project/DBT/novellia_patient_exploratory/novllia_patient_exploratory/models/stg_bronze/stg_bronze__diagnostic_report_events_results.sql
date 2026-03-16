{{ config(materialized= 'incremental',
          schema = 'staging')}}

SELECT
  de.data->>'id' AS diagnostic_report_id,
  SPLIT_PART(result->>'reference' ,'/', -1) AS observation_id,
  result->>'display' AS result_name



FROM 
  {{source('diagnostics','diagnostic_report_events')}} de
CROSS JOIN LATERAL
  jsonb_array_elements(de.data->'result') AS result

{% if is_incremental() %}

WHERE de.updated_at > (SELECT COALESCE(MAX(updated_at), '2000-01-01' )FROM {{this}})

{% endif %}

  

