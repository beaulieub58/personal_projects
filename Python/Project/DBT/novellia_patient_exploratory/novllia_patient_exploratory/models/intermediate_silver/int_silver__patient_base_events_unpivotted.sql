{{config(materialized='incremental',
  schema='intermediate',
  unique_key = 'patient_id')}}

SELECT
  pe.patient_id,
  pe.inserted_at,
  pe.updated_at,
  pe.patient_gender,
  pe.patient_birth_date,
  pe.patient_death_date,
  CASE
    WHEN pe.patient_death_date IS NULL THEN FALSE
    ELSE TRUE
  END::boolean AS patient_deceased,
  pe.patient_marital_status,
  CASE 
    WHEN pe.url_text = 'quality-adjusted-life-years' THEN CAST(pe.extension_display_name AS FLOAT) 
    ELSE NULL 
  END AS quality_adjusted_life_years,
  CASE 
    WHEN pe.url_text = 'disability-adjusted-life-years' THEN CAST(pe.extension_display_name AS FLOAT) 
    ELSE NULL 
  END AS disability_adjusted_life_years,
  CASE
    WHEN pe.url_text = 'ethnicity' THEN pe.extension_display_name
    ELSE NULL
  END AS ethnicity,
  CASE
    WHEN pe.url_text = 'mothersMaidenName' THEN pe.extension_display_name
    ELSE NULL
  END AS mothers_maiden_name,
  CASE
    WHEN pe.url_text = 'race' THEN pe.extension_display_name
    ELSE NULL
  END AS race,
  CASE
    WHEN pe.url_text = 'birthsex' THEN pe.extension_display_name
    ELSE NULL
  END AS birth_sex,
  CASE
    WHEN pe.url_text = 'birthPlace' THEN pe.extension_display_name::jsonb->>'city'
    ELSE NULL
  END AS birth_city,
  CASE
    WHEN pe.url_text = 'birthPlace' THEN pe.extension_display_name::jsonb->>'state'
    ELSE NULL
  END AS birth_state,
    CASE
    WHEN pe.url_text = 'birthPlace' THEN pe.extension_display_name::jsonb->>'country'
    ELSE NULL
  END AS birth_country

FROM
  {{ ref('stg_bronze__patient_events') }} pe

WHERE
  pe.extended_explosion_oder = 1

{% if is_incremental() %}

  AND pe.updated_at > (SELECT COALESCE(MAX(updated_at), '2000-01-01' )FROM {{this}}) 

{% endif %}