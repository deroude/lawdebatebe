insert into `user` (`type`,`display_name`) values ('MP','Parlamentescu');
insert into `user` (`type`,`display_name`) values ('LAW_OFFICER','Judecatoreanu');
insert into `user` (`type`,`display_name`) values ('LAW_OFFICER','Procurovici');
insert into `user` (`type`,`display_name`) values ('LAW_OFFICER','Namidee');
insert into `user` (`type`,`display_name`) values ('REGULAR','Pulimea');

insert into `law_article` (`proposed_by`,`agency`,`author`)
select 'UnuVerde','Parlament',`id` from `user` where `display_name`='Parlamentescu'
union select 'UnuMov','CSM',`id` from `user` where `display_name`='Judecatoreanu';

insert into `article_version` (`status`,`title`,`text`,`publish_date`,`last`,`article`)
select 'PROPOSED','Legea 0','O lege tampita',current_timestamp,1, `id` from `law_article` where `proposed_by`='UnuVerde'
union select 'IN_EFFECT','Legea 0.1','O lege la fel de tampita',current_timestamp,0, `id` from `law_article` where `proposed_by`='UnuVerde'
union select 'PROPOSED','Legea 0 barat','O alta lege tampita',current_timestamp,1, `id` from `law_article` where `proposed_by`='UnuMov';

insert into `response`(`author`,`article`,`publish_date`,`text`,`position`)
select u.`id`,a.`id`,current_timestamp,'un kkt de lege','NAY' from `user` u, `law_article` a where u.`display_name`='Procurovici' and a.`proposed_by`='UnuVerde'
union select u.`id`,a.`id`,current_timestamp,'eu zic ca da','YAY' from `user` u, `law_article` a where u.`display_name`='Pulimea' and a.`proposed_by`='UnuVerde'
union select u.`id`,a.`id`,current_timestamp,'eu cu cine votez?','ABSTAIN' from `user` u, `law_article` a where u.`display_name`='Namidee' and a.`proposed_by`='UnuVerde'
union select u.`id`,a.`id`,current_timestamp,'eu kkt si mai mare','NAY' from `user` u, `law_article` a where u.`display_name`='Pulimea' and a.`proposed_by`='UnuMov'
union select u.`id`,a.`id`,current_timestamp,'fuck yeah','YAY' from `user` u, `law_article` a where u.`display_name`='Procurovici' and a.`proposed_by`='UnuMov'
union select u.`id`,a.`id`,current_timestamp,'csf ncsf','ABSTAIN' from `user` u, `law_article` a where u.`display_name`='Namidee' and a.`proposed_by`='UnuMov';

insert into `comment`(`author`,`response`,`publish_date`,`text`)
select u.`id`,r.`id`,current_timestamp,'de ce mah?' from `user` u, `response` r where u.`display_name`='Pulimea' and r.`text`='un kkt de lege'
union select u.`id`,r.`id`,current_timestamp,'nu dadea!' from `user` u, `response` r where u.`display_name`='Procurovici' and r.`text`='eu zic ca da';

insert into `confidence_vote`(`author`,`response`,`vote_date`,`vote`)
select u.`id`,r.`id`,current_timestamp,1 from `user` u, `response` r where u.`display_name`='Pulimea' and r.`text`='un kkt de lege'
union select u.`id`,r.`id`,current_timestamp,0 from `user` u, `response` r where u.`display_name`='Procurovici' and r.`text`='eu zic ca da';