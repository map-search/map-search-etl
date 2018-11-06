# map-search-etl
네이버 캠퍼스 핵데이에서 spark을 활용한 지도 데이터 ETL을 위한 레포지터리입니다.

# 지도 검색 데이터
- 지도 검색을 위한 raw data로는 오픈스트리트맵의 특정 지역 데이터를 export해서 활용합니다. 
- https://www.openstreetmap.org

# Activity & Plan
- 실시간 지도 검색 트랜드 서비스를 만들기 위해서 필요한 작업들은 크게 세가지로 구분됩니다. 각자 역할을 나누어서 병렬적으로 진행할 예정입니다.

## 데이터 추출/정제/인덱싱
- raw data에서 데이터를 추출하고, 정제해서 ES에 적재(인덱싱)하는 과정입니다.
- 기술스택: OSP 데이터 분석, Spark2, ES HighLevelRestClient
- 구현과정:
1. Spark, Scala, ES, ES HighLevelRestClient 등 기술 스터디
2. Spark project skeleton
3. OSP의 raw 데이터 분석
4. ES 데이터 모델링(mapping) 정의
5. 데이터 추출/정제/적재를 위한 SparkJob 구현
6. SparkJob summit을 위한 배치 스크립트 작성

## 검색 엔진/환경 구축
- Elasticsearch, Kibana 등 인프라 환경을 구성하는 과정입니다.
- 기술스택: Elasticsearch, Kibana
- 구현과정: 
1. 

## 


# Communication
* Slack: naver-map-search.slack.com
* Email: jaeyong83.cho@navercorp.com
