{{ config(materialized= 'incremental',
          schema = 'staging')}}

SELECT
  de.data->>'id' AS diagnostic_report_id,
  de.inserted_at,
  de.updated_at,
  de.data->>'status' AS report_status,
  SPLIT_PART(de.data->'subject'->>'reference' ,'/', -1) AS patient_id,
  SPLIT_PART(de.data->'encounter'->>'reference' ,'/', -1) AS encounter_id,
  DATE(de.data->>'effectiveDateTime') AS report_effective_at,
  DATE(de.data->>'issued') AS report_issued_at,
  perf->>'display' AS report_performer,
  ROW_NUMBER() OVER(PARTITION BY de.data->>'id') AS report_order

FROM 
  {{source('diagnostics','diagnostic_report_events')}} de
LEFT JOIN LATERAL
  jsonb_array_elements(de.data->'performer') AS perf ON TRUE

{% if is_incremental() %}

WHERE de.updated_at > (SELECT COALESCE(MAX(updated_at), '2000-01-01' )FROM {{this}})

{% endif %}
