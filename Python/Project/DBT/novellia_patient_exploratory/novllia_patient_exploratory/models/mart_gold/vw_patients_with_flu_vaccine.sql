{{ config(materialized = 'view') }}

SELECT
  COUNT(DISTINCT i.patient_id) AS number_of_patients_with_flu_vax

FROM
  {{ ref('immunization_fct') }} i

WHERE
  i.immunization_code = '140'
  AND i.immunization_status = 'completed'
  