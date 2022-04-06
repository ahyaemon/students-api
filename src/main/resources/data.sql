SET REFERENTIAL_INTEGRITY FALSE;
truncate table student_belong_classroom;
truncate table teacher_belong_classroom;
truncate table student;
truncate table teacher;
truncate table classroom;
SET REFERENTIAL_INTEGRITY TRUE;

insert into teacher values
(1),
(2),
(3)
;

insert into student values
(1, 'student_aaa', 'yamada_taro'),
(2, 'student_bbb', 'yamada_jiro'),
(3, 'student_ccc', 'tanaka_saburo'),
(4, 'student_abc', 'tanaka_siro'),
(5, 'student_aab', 'tanaka_goro')
;

insert into classroom values
(1, 'classA'),
(2, 'classB'),
(3, 'classC')
;

insert into student_belong_classroom values
(1, 1),
(2, 1),
(3, 1),
(1, 2),
(2, 2),
(3, 2),
(4, 2),
(5, 2),
(3, 3),
(4, 3),
(5, 3)
;

insert into teacher_belong_classroom values
(1, 1),
(1, 2),
(1, 3),
(2, 1),
(2, 2),
(3, 1)
;
