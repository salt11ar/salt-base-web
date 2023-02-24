flyctl auth login 
#crear proyecto para deploy 
flyctl launch
flyctl secrets set DB_URL=jdbc:postgresql://us-east-1.18a73fb1-426d-44ff-9f37-dc07126fa74c.aws.ybdb.io:5433/yugabyte
flyctl secrets set DB_USER=admin
flyctl secrets set DB_PASSWORD=T2AirEkKdVX-A5twa03x1WYX1zO5R9
flyctl deploy