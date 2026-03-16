{{config(materialized = 'incremental',
         schema = 'intermediate',
         unique_key = ['diagnostic_report_id','report_type_coding'])}}

SELECT 
  lab.diagnostic_report_id,
  le.inserted_at,
  le.updated_at,
  lab.report_type_coding,
  lab.report_type,
  lab.report_code,
  lab.report_name,
  le.report_status,
  le.report_effective_at,
  le.report_issued_at,
  le.patient_id,
  le.encounter_id,
  le.report_performer

FROM
  {{ ref('stg_bronze__diagnostic_report_events_lab') }} lab
INNER JOIN 
  {{ ref('stg_bronze__diagnostic_report_events')}} le
  ON lab.diagnostic_report_id = le.diagnostic_report_id

WHERE
  report_code_order = 1

{% if is_incremental() %}

  AND lab.updated_at > (SELECT COALESCE(MAX(updated_at), '2000-01-01' )FROM {{this}}) 

{% endif %}
