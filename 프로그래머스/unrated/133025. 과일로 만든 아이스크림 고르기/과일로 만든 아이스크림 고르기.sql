SELECT i.flavor 
FROM FIRST_HALF f JOIN ICECREAM_INFO i 
ON f.flavor = i.flavor 
WHERE TOTAL_ORDER >= 3000 AND ingredient_type = 'fruit_based' 
ORDER BY total_order desc;