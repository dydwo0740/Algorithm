SELECT U.USER_ID, U.NICKNAME, concat(U.city,' ',U.street_address1,' ',U.street_address2) AS 전체주소, 
CONCAT(SUBSTRING(U.TLNO, 1, 3), '-', SUBSTRING(U.TLNO, 4, 4), '-', SUBSTRING(U.TLNO, 8)) AS'전화번호'
FROM USED_GOODS_USER AS U
INNER JOIN USED_GOODS_BOARD AS B
ON U.USER_ID = B.WRITER_ID
GROUP BY U.USER_ID
HAVING COUNT(*) >= 3
ORDER BY U.USER_ID DESC;