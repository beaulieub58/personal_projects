{{ config(materialized = 'view')}}

WITH chol_lab AS (
SELECT
  lab.diagnostic_report_id,
  lab.report_type_coding,
  lab.report_type,
  lab.report_issued_at,
  lab.patient_id,
  CASE 
    WHEN ((LOWER(lab.report_type) LIKE '%lipid%')
   OR (LOWER(lab.report_type) LIKE '%cholesterol%')
   OR (LOWER(lab.report_type) LIKE '%ldl%')
   OR (LOWER(lab.report_type) LIKE '%hdl%')) THEN TRUE 
    ELSE FALSE
  END AS had_chol_lab,
  ROW_NUMBER() OVER(PARTITION BY lab.patient_id ORDER BY lab.report_issued_at DESC) AS chol_lab_order

FROM
  {{ ref('lab_reports_fct') }} lab

WHERE
  lab.report_status = 'final'
  
)

SELECT
  patient_id,
  report_type,
  CASE 
    WHEN had_chol_lab = TRUE THEN report_issued_at
    ELSE NULL
  END AS last_chol_lab_report_issued,
  had_chol_lab,
  CASE 
    WHEN had_chol_lab = TRUE THEN CURRENT_DATE - report_issued_at 
    ELSE NULL
  END AS days_since_last_chol_lab

FROM 
  chol_lab

WHERE
  chol_lab_order = 1
