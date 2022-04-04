insert into teacher values
(1),
(2),
(3)
;

insert into student values
(1, 'student_1', 'taro'),
(2, 'student_2', 'jiro'),
(3, 'student_3', 'saburo'),
(4, 'student_4', 'siro'),
(5, 'student_5', 'goro')
;

insert into classroom values
(1, '上'),
(2, '中'),
(3, '下')
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
