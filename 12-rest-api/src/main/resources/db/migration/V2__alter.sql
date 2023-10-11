alter table post
    add constraint post_fk foreign key (user_id) references user_details (id);