create table vote(
  id integer identity primary key,
  electionId integer not null,
  voterId integer not null,
  candidateId integer not null
  blankVote boolean not null,
  nullVote boolean not null
);