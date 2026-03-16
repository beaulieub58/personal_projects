{{config(materialized = 'table')}}

SELECT
  lab.diagnostic_report_id,
  lab.report_type_coding,
  lab.report_status,
  lab.report_type,
  lab.report_code,
  lab.report_name,
  lab.report_effective_at,
  lab.report_issued_at,
  lab.patient_id,
  lab.encounter_id,
  lab.report_performer

FROM
  {{ ref('int_silver__diagnostic_lab_reports') }} lab