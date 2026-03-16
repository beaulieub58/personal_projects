{{ config(materialized = 'table') }}

SELECT
  oe.observation_id,
  oe.observation_status,
  oe.observation_effective_at,
  oe.observation_issued_at,
  oe.patient_id,
  oe.encounter_id,
  oe.observation_code,
  oe.observation_name_enhanced AS observation_name,
  oe.vital_sign_value,
  oe.vital_sign_unit

  FROM
    {{ ref('int_silver__observation_vital_signs') }} oe

WHERE 
  oe.observation_dupe_check = 1
  