{{ config(materialized= 'table')}}

SELECT
  pp.patient_id,
  pp.patient_gender,
  pp.patient_birth_date,
  pp.patient_death_date,
  pp.patient_deceased,
  pp.patient_marital_status,
  pp.quality_adjusted_life_years,
  pp.disability_adjusted_life_years,
  pp.ethnicity,
  pp.mothers_maiden_name,
  pp.race,
  pp.birth_sex,
  pp.birth_city,
  pp.birth_state,
  pp.birth_country,
  --MASKED VALUES START
  pi.passport_number,
  pi.drivers_license_number,
  pi.social_security_number,
  pi.medical_record_number,
  pa.patient_address_line,
  pa.patient_zip,
  pn.name_family,
  pn.name_given,
  pn.name_prefix,
  --MASKED VALUES END
  pa.patient_country,
  pa.patient_city,
  pa.patient_state


FROM
  {{ ref('int_silver__patient_base_events_pivotted') }} pp
LEFT JOIN
  {{ ref('int_silver__patient_identify_events_pivotted') }} pi
  ON pp.patient_id = pi.patient_id
LEFT JOIN
  {{ ref('int_silver__patient_address') }} pa
  ON pp.patient_id = pa.patient_id
LEFT JOIN
  {{ ref('int_silver__patient_name') }} pn
  ON pp.patient_id = pn.patient_id
  AND name_use = 'official'
