SELECT
  data->>'id' AS patient_id,
  inserted_at,
  updated_at,
  SPLIT_PART(SPLIT_PART(ext->>'url' ,'/', -1),'-',-1) AS url_text,
  COALESCE(ext_ext->'valueCoding'->>'display',
           ext->>'valueString',
           ext->>'valueCode',
           ext->>'valueAddress',
           ext->>'valueDecimal') AS extension_display_name,
  CASE WHEN naming->>'use' = 'official' THEN naming->>'prefix' ELSE NULL END AS official_prefix,
  CASE WHEN naming->>'use' = 'official' THEN naming->>'family' ELSE NULL END AS official_family_name,
  CASE WHEN naming->>'use' = 'official' THEN naming->>'given' ELSE NULL END AS official_given_name,
  CASE WHEN naming->>'use' = 'maiden' THEN naming->>'prefix' ELSE NULL END AS maiden_prefix,
  CASE WHEN naming->>'use' = 'maiden' THEN naming->>'family' ELSE NULL END AS maiden_family_name,
  CASE WHEN naming->>'use' = 'maiden' THEN naming->>'given' ELSE NULL END AS maiden_given_name,
  data->>'gender' AS patient_gender,
  DATE(data->>'birthDate') AS patient_birth_date,
  DATE(data->>'deceasedDateTime') AS patient_death_date,
  data->'maritalStatus'->>'text' AS patient_marital_status,
  com->'language'->>'text' AS patient_language,
  ad->>'city' AS patient_city,
  ad->>'state' AS patient_state,
  ad->>'postalCode' AS patient_zip


  
FROM 
  individuals.patient_events
CROSS JOIN
  jsonb_array_elements(data->'extension') AS ext
LEFT JOIN LATERAL
  jsonb_array_elements(ext->'extension') AS ext_ext ON true
CROSS JOIN
  jsonb_array_elements(data->'name') AS naming
CROSS JOIN
  jsonb_array_elements(data->'communication') AS com
CROSS JOIN
  jsonb_array_elements(data->'address') AS ad

WHERE 
  data->>'id' = '129c6ac7-8d06-89de-ad63-0204a93e76c3'
