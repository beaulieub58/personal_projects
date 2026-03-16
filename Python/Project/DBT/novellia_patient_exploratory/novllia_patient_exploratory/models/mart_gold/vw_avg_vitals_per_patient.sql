{{ config(materialized = 'view') }}
WITH counting AS (

SELECT
  o.patient_id,
  COUNT(*) AS vital_signs_measured

FROM
  {{ ref('vital_sign_details') }} o

WHERE
  o.observation_status = 'final'

GROUP BY 
  o.patient_id

)

SELECT
  ROUND(AVG(vital_signs_measured), 2) AS avg_vitals_measured_per_patient

FROM 
  counting
