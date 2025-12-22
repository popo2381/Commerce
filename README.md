# Console Commerce System - 콘솔 커머스 플랫폼
객체 지향 설계(OOP), 캡슐화, Enum, 람다 & 스트림을 활용하여

상품 조회 · 장바구니 · 주문 · 관리자 모드 기능을 구현했습니다.

### 주요 기능
#### + 상품 카테고리 & 조회
+ 카테고리 단위 상품 관리
  
+ 상품 정보
  + 상품명
  + 가격
  + 설명
  + 재고 수량
    
+ 가격 기준 필터링
  + 전체 상품
  + 100만원 이하 상품
  + 100만원 초과 상품

```
products.stream()
        .filter(p -> p.getPrice() <= 1_000_000)
        .toList();
```
    
#### + 장바구니 기능
+ 상품 선택 후 장바구니 담기
 
+ 동일 상품 중복 추가 시 수량 증가

+ 재고 초과 시 장바구니 추가 제한

+ 장바구니 내역 출력

+ 총 주문 금액 계산

```
 products.stream()
       .filter(p -> p.getPrice() <= 1_000_000)
       .toList();
```

#### + 주문 & 재고 관리
+ 장바구니 확인 후 주문 확정
  
+ 주문 시 상품 재고 차감
  
+ 주문 완료 후 장바구니 초기화

```
p.decraseStock(item.getQuantity());
```

#### + 고객 등급 & 할인
고객 등급별 할인율을 Enum 객체로 관리

```
public enum Grade {
    BRONZE(0),
    SILVER(5),
    GOLD(10),
    PLATINUM(15),
    DIAMOND(30);
}
```
+ 등급 선택 메뉴
  
+ 할인 금액 및 최종 결제 금액 계산

```
int discount = grade.computeDiscount(amount);
```

#### + 관리자 모드
+ 관리자 인증
  + 비밀번호 인증
    
  + 3회 실패 시 메인 메뉴로 복귀

+ 상품 관리
  + 상품 추가
    
  + 상품 수정
    
  + 상품 삭제
    
  + 전체 상품 현황 조회

+ 검증 로직
  + 상품명 중복 검증
    
  + 상품 삭제 시 장바구니에서도 제거
 
#### + 예외 처리
  + 잘못된 메뉴 입력 처리
    
  + 숫자가 아닌 입력 처리
    
  + 재고 부족 시 안내 메시지 출력
    
  + 장바구니가 비어 있을 경우 주문 메뉴 비활성화

#### + 프로젝트 구조
```
com.example.commerce
├── Main.java
├── CommerceSystem.java
├── CommerceView.java
├── InputReader.java
│
├── Category.java
├── Product.java
├── ProductService.java
│
├── Cart.java
├── CartItem.java
├── OrderService.java
│
├── Customer.java
├── Grade.java
│
└── AdminMode.java

```

#### + 실행 흐름
1. 프로그램 실행 -> 메인 메뉴 출력
2. 카테고리 선택
3. 상품 조회 (전체 / 가격 필터)
4. 상품 선택 후 장바구니 추가
5. 장바구니 확인
6. 주문 확정
7. 고객 등급 선택 -> 할인 적용 -> 결제 완료

#### + 실행 예시
```
[ 100만원 이하 상품 목록 ]
1. AirPods Pro | 350,000원 | 재고: 50개
0. 뒤로가기

선택한 상품: AirPods Pro | 350,000원 | 재고: 50개
1. 확인
```
```
주문이 완료되었습니다!
할인 전 금액: 350,000원
GOLD 등급 할인(10%): -35,000원
최종 결제 금액: 315,000원
AirPods Pro 재고가 50개 → 49개로 업데이트되었습니다.
```
