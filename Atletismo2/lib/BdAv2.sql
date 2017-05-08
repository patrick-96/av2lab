/*objetivo criar uma function que mostre os resultados dos atletas, o usuario pode escolher
a prova, a fase, e a bateria, caso não tenha acontecido a bateria ainda mostrar uma mensagem
E fazer a trigger que impeça o alteramento e exclusão de algumas tabelas*/

create database atletismo 
use atletismo

create table paises (
CodigoPais int identity,--pode ser tirado e colocado o coi como primary key
nomePais varchar (100),
CoiPais varchar(3)
primary key(CodigoPais)
)

insert into paises values
('Afeganistão', 'AFG'),
('Angola', 'ANG'),
('Argentina', 'ARG'),
('Australia', 'AUS'),
('Austria','AUT'),
('Bolivia', 'BOL'),
('Brasil', 'BRA'),
('Canada', 'CAN'),
('China','CHN'),
('Cuba', 'CUB'),
('Alemanha', 'GER')
select * from paises




create table atleta (
codigoAtleta int identity,
nomeAtleta varchar(100),
sexo varchar(10),
CodigoPais int--boa, aparentemento o atleta tem que ter um vinculo com o pais
primary key (codigoAtleta)
foreign key (codigoPais) references paises(CodigoPais)

)
insert into atleta values
('alex', 'masculino' , 7),
('patrick' , 'feminino',6 )

insert into atleta values
('Aderson', 'masculino', 2 )
select * from atleta

drop table competicao
drop table paises
drop table atleta
drop table provas

create table provas(
codigoProva int,
NomeProva varchar(100),
tipoProva char(2),--pra fazer alguma manipulação do tipo m=metros s=segundos ou hora, 
--isso permite eu colocar apenas um resultado na table resultado pois, ai numa function posso manipular, se for s eu converto pra o formato desejado
sexoProva varchar(10)
primary key (codigoProva)
)
select provas.NomeProva
from provas



insert into provas values 
(1, 'lançamento do dardo', 'SA','feminino'),
(2, 'salto em distancia','SA','masculino'),
(3, 'salto com vara' ,'SA' ,'masculino'),
(4, '400m com barreiras', 'C','masculino'),
(5, '100m', 'C','feminino' ),
(6, '3000m','C' ,'masculino'),
(7, 'lançamento do disco' ,'SA','masculino' ),
(8, '3000m com obstaculos', 'C' ,'feminino'),
(9, ' salto triplo','SA','feminino' ),
(10,'400m','C' , 'masculino'),
(11, '800m','C' , 'feminino'),
(12, '800m','C' , 'masculino'),
(13, '200m','C' , 'feminino'),
(14, '200m','C' , 'masculino')

select * from provas



create table competicao(codigoid int identity primary key,
codigoProva int,
codigoAtleta int,
fase varchar(100),
bateria int,
resultado varchar(50)
--bateria decimal (7,1),-- fase cade inicial final
foreign key (codigoProva) References provas(codigoProva),
foreign key (codigoAtleta) References atleta(codigoAtleta))

drop table competicao
select * from competicao

insert into competicao (codigoProva,codigoAtleta,fase,bateria,resultado)
values--(1,2,'Final',2,'11'),
(4,3,'Inicial',1,'01:10:20.085'),
(4,1,'Inicial',1,'DNF')
select * from atleta
select * from provas
select * from competicao





--trigger para não deixar homens se registrarem em provas femininas e vice-versa
create trigger t_genero
on competicao
for insert
as
begin
declare 
@atleta as int,
@prova as int,
@generoAtleta as varchar(10),
@generoProva as varchar(10)
select @atleta= codigoAtleta from inserted
select @prova= codigoProva from inserted
set @generoAtleta=(select atleta.sexo from atleta where atleta.codigoAtleta=@atleta)
set @generoProva=(select provas.sexoProva from provas where provas.codigoProva=@prova)
if @generoProva =@generoAtleta
begin
select *from inserted
end
else if @generoProva <> @generoAtleta
 begin
 rollback transaction
 raiserror ('Os gêneros do atleta e prova devem coincidir',16,1)
 end
 end

 drop trigger t_genero

 --triggers para não permitir alterar nem excluir tabelas com o resultado, paises e atletas, e não permitir colocarem outros se não os oito melhores atletas
 create trigger t_youshallnotpassA
 on atleta
 for update,delete
 as
 begin
 rollback transaction
 raiserror('Não é permitido alterar nem excluir está table',10,2)
 end

 create trigger t_youshallnotpassP
 on provas
 for update,delete
 as
 begin
 rollback transaction
 raiserror('Não é permitido alterar nem excluir está table',10,2)
 end

 create trigger t_youshallnotpassC
 on competicao
 for update,delete
 as
 begin
 rollback transaction
 raiserror('Não é permitido alterar nem excluir esta tabela',10,2)
 end


 /*create trigger t_youshallnotpass8, fazer o negocio de impedir de colocar mais do q uma corrida em cada uma das fase
 on competicao
 for insert
 as begin
 DECLARE 
 @indice as int,
 @total as int,
 @fase as varchar(50),
 @prova as varchar(100),
 @genero as varchar(50)
 set @indice= (select min(competicao.codigoid) from inserted)
 set @total= (select max(competicao.codigoid) from inserted)
 while @indice <= @total+1
 begin
 set @prova=(select provas.NomeProva from provas inner join competicao on provas.codigoProva=competicao.codigoProva  where competicao.codigoid=@indice)
 set @fase =(select competicao.fase from competicao where competicao.codigoid=@indice)
 set @genero=(select provas.sexoProva from provas inner join competicao on provas.codigoProva=competicao.codigoProva where competicao.codigoid=@indice )
 if @fase like 'Final'
 begin

 

 end
 end*/

 

 
 
 

 select distinct top 3 (competicao.codigoAtleta) as atleta,min(competicao.resultado) as melhor_resultado
 from competicao
 where resultado <> 'DNF'
 group by competicao.codigoAtleta
 select top 3 distinct(competicao.codigoAtleta)
 from competicao


 ----------------------------------------------------------------------------------------------------------
  select top 2
 from (select Distinct(atleta.nomeAtleta),provas.NomeProva,min(competicao.resultado)
 from competicao
 inner join provas
 on competicao.codigoProva=provas.codigoProva
 inner join atleta
 on competicao.codigoAtleta=atleta.codigoAtleta
 where provas.NomeProva='400m com barreiras'
 group by atleta.nomeAtleta,provas.NomeProva)
  
  
  select Distinct(atleta.nomeAtleta),provas.NomeProva,min(competicao.resultado)
 from competicao
 inner join provas
 on competicao.codigoProva=provas.codigoProva
 inner join atleta
 on competicao.codigoAtleta=atleta.codigoAtleta
 where provas.NomeProva='400m com barreiras' and select top <3
 group by atleta.nomeAtleta,provas.NomeProva

 select Distinct(atleta.nomeAtleta),min(competicao.resultado) 
 from atleta
 inner join competicao
 on atleta.codigoAtleta=competicao.codigoAtleta
 group by atleta.nomeAtleta

 create function fn_finalistas(@nomeProva varchar(100))
  returns @tabela Table(
 nomeProva Varchar(200),
 nomeAtleta varchar(200),
 resultado varchar(100))
 as begin
 declare @tipoProva char(2),
 @indice int,
 @total int
  set @tipoProva=(select provas.tipoProva from provas where provas.NomeProva=@nomeProva)
 if @tipoProva='C'
 begin 
 insert into @tabela(nomeProva,nomeAtleta,resultado)
 select top 8 provas.NomeProva,Distinct(atleta.nomeAtleta),min(competicao.resultado)
 from competicao
 inner join provas
 on competicao.codigoProva=provas.codigoProva
 inner join atleta
 on competicao.codigoAtleta=atleta.codigoAtleta
 where provas.NomeProvas=@nomeProva
 end
 else if @tipoProva='SA'
 begin
 insert into @tabela(nomeProva,nomeAtleta,resultado)
 select top 8 provas.NomeProva,Distinct(atleta.nomeAtleta),min(competicao.resultado)
 from competicao
 inner join provas
 on competicao.codigoProva=provas.codigoProva
 inner join atleta
 on competicao.codigoAtleta=atleta.codigoAtleta
 where provas.NomeProvas=@nomeProva
 end
 end


 


--
CREATE FUNCTION fn_tabela(@nomeProva varchar(100),@fase varchar(100),@bateria varchar(1))
RETURNS @tabela TABLE(
nomeProva VARCHAR(200),
nomeAtleta varchar(200),
fase varchar(100),
bateria int,
resultado varchar(100))
as 
begin
declare @tipoProva  char(2)
declare @indice int
declare @total int
declare @nchar int
set @indice=1
set @total =(select count(competicao.codigoid) from competicao
inner join provas
on competicao.codigoProva=provas.codigoProva
inner join atleta
on competicao.codigoAtleta=atleta.codigoAtleta
where provas.NomeProva=@nomeProva and competicao.fase=@fase and competicao.bateria=@bateria)
set @tipoProva=(select provas.tipoProva from provas where provas.NomeProva=@nomeProva) 
if @tipoProva like 'C'
begin
while @indice <= @total+1
begin
set @nchar= (select len(competicao.resultado)
from competicao
where competicao.codigoid=@indice) 
if @nchar <= 3
begin
insert into @tabela(nomeProva,nomeAtleta,fase,bateria,resultado)
select provas.NomeProva,atleta.nomeAtleta,competicao.fase,competicao.bateria,competicao.resultado 
from competicao
inner join provas
on competicao.codigoProva=provas.codigoProva
inner join atleta
on competicao.codigoAtleta=atleta.codigoAtleta
where provas.NomeProva=@nomeProva and competicao.fase=@fase and competicao.bateria=@bateria and competicao.codigoid=@indice
end
else if @nchar >3
begin
insert into @tabela(nomeProva,nomeAtleta,fase,bateria,resultado)
select provas.NomeProva,atleta.nomeAtleta,competicao.fase,competicao.bateria,cast(competicao.resultado as time) as resultado
from competicao
inner join provas
on competicao.codigoProva=provas.codigoProva
inner join atleta
on competicao.codigoAtleta=atleta.codigoAtleta
where provas.NomeProva=@nomeProva and competicao.fase=@fase and competicao.bateria=@bateria and competicao.codigoid=@indice
end
set @indice=@indice+1
end
end
else if @tipoProva like 'SA'
begin
insert into @tabela(nomeProva,nomeAtleta,fase,bateria,resultado)
select provas.NomeProva,atleta.nomeAtleta,competicao.fase,competicao.bateria,competicao.resultado 
from competicao
inner join provas
on competicao.codigoProva=provas.codigoProva
inner join atleta
on competicao.codigoAtleta=atleta.codigoAtleta
where provas.NomeProva=@nomeProva and competicao.fase=@fase and competicao.bateria=@bateria
end
return 
end




--usar esse
select * --nomeProva,nomeAtleta,fase,bateria,resultado 
 from fn_tabela('400m com barreiras','Inicial',1)
ORDER BY resultado asc 

select top 8 nomeProva,nomeAtleta,fase,bateria,resultado from fn_tabela('400m com barreiras','Inicial',1)
where resultado <> 'DNF'
order by resultado desc 


select provas.NomeProva,Distinct(atleta.nomeAtleta),competicao.resultado


select * from provas
select * from competicao
drop Function fn_tabela

--testando 
create function fn_contatotal(@nomeProva varchar(100),@fase varchar(100),@bateria int)
returns int
as begin
declare
@total int
set @total= (select count(competicao.codigoid) from competicao
inner join provas
on competicao.codigoProva=provas.codigoProva
inner join atleta
on competicao.codigoAtleta=atleta.codigoAtleta
where provas.NomeProva=@nomeProva and competicao.fase=@fase and competicao.bateria=@bateria)
return @total 
end

select * from fn_contatotal('400m com barreiras','Inicial',1)
select dbo.fn_contatotal('400m com barreiras','Inicial',1) as total
drop function fn_contatotal

select LEN(competicao.resultado)
from competicao












select Distinct(competicao.fase)
from competicao 
ORDER BY competicao.fase desc

select Distinct(competicao.bateria)
from competicao
order by competicao.bateria asc




--ISNULL(competicao.resultadoT,competicao.resultadoM),
--parecido com o select * from competicao mas com os nomes da prova e atleta
select provas.NomeProva,atleta.nomeAtleta,competicao.fase,competicao.bateria,competicao.resultado
from competicao
inner join provas
on competicao.codigoProva=provas.codigoProva
inner join atleta
on competicao.codigoAtleta=atleta.codigoAtleta
where competicao.bateria=2 



select provas.NomeProva,atleta.nomeAtleta,competicao.fase,competicao.bateria,competicao.resultado
from competicao
inner join provas
on competicao.codigoProva=provas.codigoProva
inner join atleta
on competicao.codigoAtleta=atleta.codigoAtleta
where competicao.bateria=2 and provas.tipoProva like 'C' and cast(

select * from competicao
select cast(competicao.resultado as time)  
from competicao
where competicao.codigoid=4--codigoProva=4 and competicao.codigoProva=4

SELECT CONVERT(time(3) ,competicao.resultado)
from competicao
where competicao.codigoAtleta=2

--pega o melhor resultado do meters e inicial de determinado atleta
select atleta.nomeAtleta,max(competicao.resultadoM)
from atleta
inner join competicao
on atleta.codigoAtleta=competicao.codigoAtleta
where competicao.fase like 'Inicial'
group by atleta.nomeAtleta





insert into competicao values
(2,3,1.20)




select atleta.codigoAtleta,Atleta.nomeAtleta, provas.NomeProva, provas.codigoProva,competicao.bateria, paises.CoiPais 
from atleta
inner join competicao on atleta.codigoAtleta = competicao.codigoAtleta
inner join provas on provas.codigoProva = competicao.codigoProva
inner join paises on atleta.CodigoPais = paises.CodigoPais
order by competicao.bateria



