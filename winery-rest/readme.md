## Creates harvest
curl -X POST -i -H "Content-Type: application/json" --data '{"harvestYear":"2020","quality":"LOW","quantity":"20","grapeId":"1"}' http://localhost:8080/pa165/rest/harvests/create


## Gets harvest with specified id
curl -i -X GET "http://localhost:8080/pa165/rest/harvests/1"


## Gets list of all harvests
curl -i -X GET "http://localhost:8080/pa165/rest/harvests/list"


## Update one specific harvest
curl -X PUT -i -H "Content-Type: application/json" --data '{"id":"1","harvestYear":"1000","quality":"LOW","quantity":"90"}' "http://localhost:8080/pa165/rest/harvests/update"


## Remove one specific harvest
curl -i -X DELETE "http://localhost:8080/pa165/rest/harvests/remove/1"
