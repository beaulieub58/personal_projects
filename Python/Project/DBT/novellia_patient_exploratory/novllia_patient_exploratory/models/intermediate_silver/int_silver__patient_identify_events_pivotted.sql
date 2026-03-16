{{config(materialized='table',
  schema='intermediate')}}

SELECT
  pe.patient_id,
  pe.inserted_at,
  pe.updated_at,
  MAX(passport_number) AS passport_number,
  MAX(drivers_license_number) AS drivers_license_number,
  MAX(social_security_number) AS social_security_number,
  MAX(medical_record_number) AS medical_record_number


FROM
  {{ ref('int_silver__patient_identify_events_unpivotted') }} pe

GROUP BY
  pe.patient_id,
  pe.inserted_at,
  pe.updated_at
  