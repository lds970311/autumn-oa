select distinct sm.*
from sys_user su
         left join sys_user_role sur on su.id = sur.user_id
         left join sys_role sr on sur.role_id = sr.id
         left join sys_role_menu srm on srm.role_id = sr.id
         left join sys_menu sm on sm.id = srm.menu_id
where su.id = 2
  and sm.status = 1
  and sur.is_deleted = 0
  and sm.is_deleted = 0
  and srm.is_deleted = 0;


select *
from sys_menu m
         inner join sys_role_menu rm on rm.menu_id = m.id
         inner join sys_user_role ur on ur.role_id = rm.role_id
where ur.user_id = 1
  and m.status = 1
  and rm.is_deleted = 0
  and ur.is_deleted = 0
  and m.is_deleted = 0