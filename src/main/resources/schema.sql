create table teacher(
    id integer primary key
);

create table student(
    id integer primary key,
    login_id text not null,
    name text not null
);

create table classroom(
    id integer primary key,
    name text not null
);

create table student_belong_classroom(
    student_id integer not null references student(id),
    classroom_id integer not null references classroom(id)
);

create table teacher_belong_classroom(
    teacher_id integer not null references teacher(id),
    classroom_id integer not null references classroom(id)
)
