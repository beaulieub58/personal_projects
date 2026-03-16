{{config(materialized = 'table')}}

SELECT
  i.immunization_id,
  i.immunization_status,
  i.immunization_occured_at,
  i.immunization_primary_source_ind,
  i.immunization_site,
  i.patient_id,
  i.encounter_id,
  i.immunization_type,
  i.immunization_code

FROM 
  {{ ref('int_silver__immunization') }} i