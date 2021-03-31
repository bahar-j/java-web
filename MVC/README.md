
### 게시판 w. JSP, Servlet, MyBatis, Oracle

#### 기능 📌
- 글 생성, 수정, 조회, 삭제 (CRUD)
- 페이지네이션

#### 구조 🧩


`request가 들어왔을 때`
Request -> Controller -> Action -> Service -> DAO(Database Access Object) -> DB

`view에 보여줄 때`
View <- Controller <- Action <- Service <- DAO(Database Access Object) <- DB

**View** : JSP <br>

**Controller** : Servlet (MyController.java)
- request를 받는다.
- request uri에 따라 적절한 Action(비즈니스 로직 수행)을 호출한다.

**DAO(repository)** : SQL을 사용하여 데이터베이스에 실제로 접근하는 객체 (BoardDao.java) <br>

**DTO(Data Transfer Object)** : DB에서 얻은 데이터는 DTO에 담아서 교환된다. (ListModel.java) <br>

**Entity Class** : 실제 DB 테이블과 매칭되는 클래스(Board.java)
Entity Class와 DTO가 따로 존재하는 이유는, View Layer와 DB Layer의 역할을 철저히 분리하기 위함이다. Entity Class는 Domain Logic 외에 Presentation Logic을 가져서는 안된다. <br>

