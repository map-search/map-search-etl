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
  2. Spark 설치 및 project skeleton
  3. OSP의 raw 데이터 분석
  4. ES 데이터 모델링(mapping) 정의
  5. 데이터 추출/정제/적재를 위한 SparkJob 구현
  6. SparkJob summit을 위한 배치 스크립트 작성

## 검색 인프라 구축
- Elasticsearch, Kibana 인프라 환경을 구성하는 과정입니다.
- 기술스택: Elasticsearch, Kibana, Logstash
- 구현과정: 
  1. Elasticsearch, Kibana, Logstash 구축
  2. 필요시, ES Plugin 리서치 및 설치
  3. 로깅 포멧 설계 및 분석을 위한 시스템(ELK) 구성
  4. Kibana 데이터 분석 및 실시간 급상승 장소 페이지 구성
  
## 검색 서빙 레이어 개발
- Spring을 활용하여 검색 서빙을 설계하고 개발합니다. 
- 기술스택: Spring Boot 2.0, java or kotlin, ES HighLevelRestClient
- 구현과정: 
  1. Spring Boot 2.0, Flux, ES 등 스터디
  2. Project skeleton
  3. 검색 API 설계 및 개발
  4. 실시간 트랜드 API 설계 및 개발

# Communication
* Slack: naver-map-search.slack.com
* Email: jaeyong83.cho@navercorp.com
