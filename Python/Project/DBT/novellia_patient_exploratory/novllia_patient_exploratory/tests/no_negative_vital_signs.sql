SELECT *
FROM {{ ref('vw_avg_vitals_per_patient') }}
WHERE COALESCE(avg_vitals_measured_per_patient,0) < 0 