SELECT *
FROM {{ ref('vw_most_recent_cholesterol_lab') }}
WHERE COALESCE(days_since_last_chol_lab,0) < 0 