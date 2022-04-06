create table if not exists teacher (
    id integer primary key
);

create table if not exists student(
    id integer primary key,
    login_id text not null,
    name text not null
);

create table if not exists classroom(
    id integer primary key,
    name text not null
);

create table if not exists student_belong_classroom(
    student_id integer not null references student(id),
    classroom_id integer not null references classroom(id)
);

create table if not exists teacher_belong_classroom(
    teacher_id integer not null references teacher(id),
    classroom_id integer not null references classroom(id)
)
