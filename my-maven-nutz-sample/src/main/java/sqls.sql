/*插入User数据*/
/*user_insert*/
insert into t_user(id, username, password, info, emails) values(@id, @username, @password, @info, @emails)


/*查询所有User数据*/
/*user_selectAll*/
select * from t_user

/*查询某个User*/
/*user_selectOne*/
select * from t_user where id = @id


/*插入Article数据*/
/*article_insert*/
insert into t_article(id, title, abstractContent, content, username, times) values(@id, @title, @abstractContent, @content, @username, @times)


/*查询所有Article数据*/
/*article_selectAll*/
select * from t_article


/*查询某篇Article*/
/*article_selectOne*/
select * from t_article where id = @id





