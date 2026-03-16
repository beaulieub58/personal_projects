{{config(materialized='table',
  schema='intermediate')}}

SELECT
  pe.patient_id,
  pe.inserted_at,
  pe.updated_at,
  pe.patient_gender,
  pe.patient_birth_date,
  pe.patient_death_date,
  pe.patient_deceased,
  pe.patient_marital_status,
  MAX(quality_adjusted_life_years) AS quality_adjusted_life_years,
  MAX(disability_adjusted_life_years) AS disability_adjusted_life_years,
  MAX(ethnicity) AS ethnicity,
  MAX(mothers_maiden_name) AS mothers_maiden_name,
  MAX(race) AS race,
  MAX(birth_sex) AS birth_sex,
  MAX(birth_city) AS birth_city,
  MAX(birth_state) AS birth_state,
  MAX(birth_country) AS birth_country

FROM
  {{ ref('int_silver__patient_base_events_unpivotted') }} pe

GROUP BY
  pe.patient_id,
  pe.inserted_at,
  pe.updated_at,
  pe.patient_gender,
  pe.patient_birth_date,
  pe.patient_death_date,
  pe.patient_deceased,
  pe.patient_marital_status