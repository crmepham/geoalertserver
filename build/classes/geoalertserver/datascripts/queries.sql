select u.username, u.status, c.userId, c.contactId from contact c
join user u on c.contactId=u.userId
where c.userId =(select userId from user where username='ccc');