create database av1
go
use av1
drop table Quesito


create function fn_separanota1(@id_escola int, @id_quesito int)
returns decimal(4,1)
as
begin
declare
@nota1 decimal(4,1)
set @nota1 = (select nota from Nota where id_escola = @id_escola and id_quesito = @id_quesito and id_jurado = 1)
return @nota1
end

create function fn_separanota2(@id_escola int, @id_quesito int)
returns decimal(4,1)
as
begin
declare
@nota2 decimal(4,1)
set @nota2 = (select nota from Nota where id_escola = @id_escola and id_quesito = @id_quesito and id_jurado = 2)
return @nota2
end

create function fn_separanota3(@id_escola int, @id_quesito int)
returns decimal(4,1)
as
begin
declare
@nota3 decimal(4,1)
set @nota3 = (select Nota from nota where id_escola = @id_escola and id_quesito = @id_quesito and id_jurado = 3)
return @nota3
end

create function fn_separanota4(@id_escola int, @id_quesito int)
returns decimal(4,1)
as
begin
declare
@nota4 decimal(4,1)
set @nota4 = (select Nota from nota where id_escola = @id_escola and id_quesito = @id_quesito and id_jurado = 4)
return @nota4
end

create function fn_separanota5(@id_escola int, @id_quesito int)
returns decimal(4,1)
as
begin
declare
@nota5 decimal(4,1)
set @nota5 = (select nota from Nota where id_escola = @id_escola and id_quesito = @id_quesito and id_jurado = 5)
return @nota5
end


create procedure pc_verificaQ(@quesito varchar(100))
as
declare
@id_quesito int
set @id_quesito = (select id_q from Quesito where nome_q = @quesito)
select nome_e as Escola ,nome_q as Quesito, dbo.fn_separanota1(id_e, id_q) as nota1,dbo.fn_separanota2(id_e, id_q) as nota2,dbo.fn_separanota3(id_e, id_q) as nota3,dbo.fn_separanota4(id_e, id_q)as nota4,dbo.fn_separanota5(id_e, id_q) as nota5, dbo.fn_maior(id_e) as Maior_Descartada, dbo.fn_menor(id_e) as Menor_Descarta,dbo.fn_total(id_e) as total
from Quesito 
inner join Nota 
on Quesito.id_q = Nota.id_quesito
inner join Escola 
on Nota.id_escola = Escola.id_e
where Nota.id_quesito = @id_quesito
group by nome_e,nome_q, id_q, id_e

exec pc_verificaQ 'Comissão de Frente'


select nome_e,nome_q, dbo.fn_separanota1(id_e, id_quesito) as nota1,dbo.fn_separanota2(id_e, id_quesito) as nota2,dbo.fn_separanota3(id_e, id_quesito) as nota3,dbo.fn_separanota4(id_e, id_quesito)as nota4,dbo.fn_separanota5(id_e, id_quesito) as nota5, dbo.fn_maior(id_e) as Maior_Descartada, dbo.fn_menor(id_e) as Menor_Descarta,dbo.fn_total(id_e) as total
from Quesito 
inner join Nota 
on Quesito.id_q= Nota.id_quesito
inner join Escola 
on Nota.id_escola = Escola.id_e
where Nota.id_quesito = id_quesito
group by nome_e, nome_q, id_quesito, id_e

select * from Quesito 

create procedure sp_inserenota(@nota decimal(3,1),@nome_e varchar(100),@nome_j varchar(100),@nome_q varchar(100))
as
declare
@id_e int,
@id_j int,
@id_q int 
set @id_e=(select id_e from Escola where @nome_e=nome_e)
set @id_j=(select id_j from Jurado where @nome_j=nome_j)
set @id_q=(select id_q from Quesito where @nome_q=nome_q)
insert into Nota (nota,id_escola,id_jurado,id_quesito)values
(@nota,@id_e,@id_j,@id_q)

select * from Nota
exec sp_inserenota 1,'Acadêmicos do Tatuapé','1º Jurado','Comissão de Frente'
exec pc_sambao 'Acadêmicos do Tatuapé', 'Comissão de Frente', '1º Jurado', 10.0
drop table Nota



create function fn_menor(@id_escola int)
returns decimal (4,1)
as
begin
	declare 
			@menor decimal(4,1)
			select @menor=cast(min(nota) as decimal(4,1)) from Nota where id_escola=@id_escola
			return @menor
			end

			select dbo.fn_menor(1)


create function fn_maior(@id_escola int)
returns decimal (3,1)
as
begin
	declare 
			@maior decimal(4,1)
			select @maior=cast(max(nota) as decimal(4,1)) from Nota where id_escola=@id_escola
			return @maior
			end

			select dbo.fn_menor(1)

create function fn_total(@id_escola int)
returns decimal(4,1)
as
begin
	declare @soma decimal(4,1),
			@nota decimal(4,1),
			@id_jurado int,
			@id_quesito int = 1,
			@menor decimal (3,1),
			@maior decimal(3,1)

			/*while(Select Count(*) From Nota where id_escola=@id_escola)>0
			select @soma= SUM(nota) as total decimal(3,1) from Nota where id_escola=@id_escola
			return @soma
		    end*/
select @menor=dbo.fn_menor(@id_escola) 
select @maior= dbo.fn_maior(@id_escola)
select @nota = cast(sum(nota) as decimal(4,1)) from nota where id_escola = @id_escola
set @soma= @nota-(@menor+@maior)
return @soma
end

create function fn_quesito(@id_quesito int)
returns decimal(4,1)
as 
begin
declare @soma decimal(4,1),
@menor decimal(3,1),
@maior decimal(3,1)
select @soma=cast(max(nota) as decimal(4,1)) from Nota 
inner join Quesito
on Nota.id_quesito=Quesito.id_q
where id_q= @id_quesito
return @soma
end

select nome_e,nome_q,nome_j,nota ,dbo.fn_maior(id_e) as Maior_Descartada, dbo.fn_menor(id_e) as Menor_Descarta,dbo.fn_total(id_e) as total
from Quesito
inner join Nota
on Quesito.id_q=Nota.id_quesito
inner join Escola
on Nota.id_escola=Escola.id_e
inner join Jurado
on Nota.id_jurado=id_j




select nome_e,nome_q ,nota,dbo.fn_quesito(1) as Soma
from Quesito
inner join Nota
on Quesito.id_q=Nota.id_quesito
inner join Escola
on Nota.id_escola=Escola.id_e



select nome_e,dbo.fn_total(id_e) as total_pontos
from Escola
order by total_pontos
<-- fazer uma function pra cada sum etc-->
create function fn_total2()
returns @tabela table(
nome_e(100),
total_pontos decimal(3,1))
as begin
insert @tabela(nome_e,total_pontos)
select nome_e, total_pontos from Escola

select min(nota) as menor
from Nota

select * from Nota
select dbo.fn_total(1) as soma

create function fn_totalzin(@id_escola int)
returns decimal(4,1)
as 
begin
declare @soma decimal(4,1)
select @soma= SUM(nota) as total
from Nota
where id_escola=@id_escola
end
return @soma
end

select SUM(nota) as total
from Nota


drop function fn_sambao

select dbo.fn_sambao(1) as soma

create table Quesito(id_q int identity(1,1) primary key,
nome_q varchar(100))

drop table Quesito
drop table Escola
drop table Jurado
drop table Nota

create table Jurado(id_j int identity primary key,
nome_j varchar(100))

create table Escola(id_e  int identity(1,1)  primary key,
nome_e varchar(150),
total_pontos decimal(3,1))

create table Nota(id_n int identity primary key,
nota decimal(3,1),
id_escola int foreign key references Escola ,
id_quesito int foreign key  references Quesito ,
id_jurado int foreign key references Jurado)



select * from Quesito
select * from Escola
select * from Jurado
select * from Nota

insert into Quesito(nome_q) values 
('Comissão de Frente'),
('Evolução'),
('Fantasia'),
('Bateria'),
('Alegoria'),
('Harmonia'),
('Samba-Enredo'),
('Mestre-Sala e Porta-Bandeira'),
('Enredo')

insert into Escola(nome_e)
values
('Acadêmicos do Tatuapé'),
('Rosas de Ouro'),
('Mancha Verde'),
('Vai-Vai'),
('X-9 Paulistana'),
('Dragões da Real'),
('Águia de Ouro'),
('Nenê de Vila Matilde'),
('Gaviões da Fiel'),
('Mocidade Alegre'),
('Tom Maio'),
('Unidos de Vila Maria'),
('Acadêmicos do Tucuruvi'),
('Império de Casa Verde')

insert into Jurado(nome_j)
values
('1º Jurado'),
('2º Jurado'),
('3º Jurado'),
('4º Jurado'),
('5º Jurado')




select * from Nota