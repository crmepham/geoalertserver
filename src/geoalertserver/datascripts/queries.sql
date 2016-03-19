// get user contacts
select u.username, u.fullName, u.status, c.userId, c.contactId 
from contact c
join user u on c.contactId=u.userId
where c.userId =(select userId from user where username='ccc') 
and accepted=1;

// get user pending contact requests
select u.username, u.fullName, u.status, c.userId, c.contactId 
from contact c
join user u on c.userId=u.userId
where c.contactId =(select userId from user where username='ccc') 
and accepted=0;

// get user notifications
select u.fullName, u.username, u.status, u.nextOfKinContactNumber as nokNumber
from user u
join notification n on n.userId = u.userId
where senderId=2
and n.deleted=0;