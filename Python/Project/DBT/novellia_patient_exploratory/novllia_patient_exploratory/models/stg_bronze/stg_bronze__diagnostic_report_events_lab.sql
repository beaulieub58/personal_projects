{{ config(materialized= 'incremental',
          schema = 'staging')}}

WITH all_diagnostics AS (
SELECT
  de.data->>'id' AS diagnostic_report_id,
  de.data->'code'->'coding'->0->>'code' AS report_type_coding,
  de.data->'code'->>'text' AS report_type,
  coding->>'code' AS report_code,
  coding->>'display' AS report_name


FROM 
  {{source('diagnostics','diagnostic_report_events')}} de
LEFT JOIN LATERAL
  jsonb_array_elements(de.data->'category') AS cat ON TRUE
LEFT JOIN LATERAL
  jsonb_array_elements(cat->'coding') AS coding ON TRUE

{% if is_incremental() %}

WHERE de.updated_at > (SELECT COALESCE(MAX(updated_at), '2000-01-01' )FROM {{this}})

{% endif %}
)

SELECT
  diagnostic_report_id,
  report_type_coding,
  report_type,
  report_code,
  report_name,
  ROW_NUMBER() OVER(PARTITION BY diagnostic_report_id, report_type_coding) AS report_code_order
FROM 
  all_diagnostics

WHERE 
  LOWER(report_code) = 'lab'
  