
    alter table `accounting_record` 
       drop 
       foreign key `FK41jm4vk7runvmg5tderffrele`;

    alter table `accounting_record` 
       drop 
       foreign key `FKcggg8hcmhohhlaeka6ov3thfh`;

    alter table `activity` 
       drop 
       foreign key `FK8cnxt6m91noqrbuk63luhvebv`;

    alter table `administrator` 
       drop 
       foreign key FK_2a5vcjo3stlfcwadosjfq49l1;

    alter table `anonymous` 
       drop 
       foreign key FK_6lnbc6fo3om54vugoh8icg78m;

    alter table `application` 
       drop 
       foreign key `FKp6hc01e2bc6ly2q8rv4b7ubro`;

    alter table `application` 
       drop 
       foreign key `FK30l9w5a7tidtoccodjps8rane`;

    alter table `application` 
       drop 
       foreign key `FKl4fp0cd8c008ma79n6w58xhk9`;

    alter table `authenticated` 
       drop 
       foreign key FK_h52w0f3wjoi68b63wv9vwon57;

    alter table `bookkeeper` 
       drop 
       foreign key FK_krvjp9eaqyapewl2igugbo9o8;

    alter table `consumer` 
       drop 
       foreign key FK_6cyha9f1wpj0dpbxrrjddrqed;

    alter table `entrepreneur` 
       drop 
       foreign key FK_r6tqltqvrlh1cyy8rsj5pev1q;

    alter table `forum` 
       drop 
       foreign key `FKfyiav75cmsy3piffwwesx650x`;

    alter table `investment` 
       drop 
       foreign key `FKk1kua11epb11pnocw4pcgndn1`;

    alter table `investor` 
       drop 
       foreign key FK_dcek5rr514s3rww0yy57vvnpq;

    alter table `message` 
       drop 
       foreign key `FKfwwpivgx5j4vw4594dgrw884q`;

    alter table `message` 
       drop 
       foreign key `FKik4epe9dp5q6uenarfyia7xin`;

    alter table `provider` 
       drop 
       foreign key FK_b1gwnjqm6ggy9yuiqm0o4rlmd;

    drop table if exists `accounting_record`;

    drop table if exists `activity`;

    drop table if exists `administrator`;

    drop table if exists `anonymous`;

    drop table if exists `application`;

    drop table if exists `authenticated`;

    drop table if exists `banner`;

    drop table if exists `barba_bulletin`;

    drop table if exists `bookkeeper`;

    drop table if exists `challenge`;

    drop table if exists `consumer`;

    drop table if exists `customisation`;

    drop table if exists `entrepreneur`;

    drop table if exists `esquen_bulletin`;

    drop table if exists `forum`;

    drop table if exists `inquire`;

    drop table if exists `investment`;

    drop table if exists `investor`;

    drop table if exists `lucas_bulletin`;

    drop table if exists `message`;

    drop table if exists `notice`;

    drop table if exists `overture`;

    drop table if exists `provider`;

    drop table if exists `technology_record`;

    drop table if exists `tool`;

    drop table if exists `user_account`;

    drop table if exists `hibernate_sequence`;
