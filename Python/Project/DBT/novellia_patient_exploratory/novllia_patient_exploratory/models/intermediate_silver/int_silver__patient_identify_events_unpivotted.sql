{{ config(materialized= 'incremental',
          schema = 'intermediate',
          unique_key = 'patient_id')}}
SELECT
  pe.patient_id,
  pe.inserted_at,
  pe.updated_at,
  CASE 
    WHEN pe.id_name = 'Passport Number' THEN pe.id_value
    ELSE NULL 
  END AS passport_number,
  CASE 
    WHEN pe.id_name = 'Driver''s license number' THEN pe.id_value
    ELSE NULL 
  END AS drivers_license_number,
  CASE 
    WHEN pe.id_name = 'Social Security Number' THEN pe.id_value
    ELSE NULL 
  END AS social_security_number,
  CASE 
    WHEN pe.id_name = 'Medical Record Number' THEN pe.id_value
    ELSE NULL 
  END AS medical_record_number

FROM
  {{ ref('stg_bronze__patient_events_identify') }} pe

WHERE
  pe.patient_id_order = 1

{% if is_incremental() %}

  AND pe.updated_at > (SELECT COALESCE(MAX(updated_at), '2000-01-01' )FROM {{this}}) 

{% endif %}