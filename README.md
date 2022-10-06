# # Java_App_Board_SpringFramework_v7

# 1.프로젝트 개요
### 1.1 프로젝트 목적
- Spring Framework, Spring Boot를 활용한 게시판 Application 개발

### 1.2 목표 및 의의
#### 1.2.1 Java_App_Board_JSP_MVC_Model1_V5
- Spring Framework 이해
- 스프링 AOP에 대한 이해 
- @어노테이션 
- 회원가입시 input값 유효성 검사
- 비동기 JavaScript ajax를 사용한 id 중복 check 구현
- 단방향 암호화인 SHA-256 알고리즘을 이용한 PassWord 암호화


# 2. 개발 환경
- IntelliJ IDEA(Ultimate Edition), GitHub


# 3. 사용기술
- Java 11, JavaScript, JSP, HTML 5


# 4.프로젝트 설계

### 4.1 로그인 (Login page) 
<img width="1105" alt="스크린샷 2022-10-01 오후 2 17 26" src="https://user-images.githubusercontent.com/103010985/193393815-cdd7b9c7-8e28-4c4d-9560-825448cf3730.png">

### 4.2 회원가입 (id 중복 확인)
<img width="1091" alt="스크린샷 2022-10-01 오후 2 38 47" src="https://user-images.githubusercontent.com/103010985/193394439-5743c283-91e8-4fb3-ade1-737113b86814.png">
<img width="1086" alt="스크린샷 2022-10-01 오후 2 39 04" src="https://user-images.githubusercontent.com/103010985/193394449-dc8d1b15-bd32-477a-aaea-37e3d397ecfd.png">


### 4.2 게시판 (board page) 
<img width="1099" alt="스크린샷 2022-10-01 오후 2 21 47" src="https://user-images.githubusercontent.com/103010985/193393947-92d5eb51-a4e1-4904-9c9f-40e80a7be1d5.png">

### 4.2 게시글 작성하기
<img width="1092" alt="스크린샷 2022-10-01 오후 2 23 57" src="https://user-images.githubusercontent.com/103010985/193394009-2fd1b4b4-cedd-46f7-87c5-2cf524ce9c24.png">

### 4.2.1 게시글 작성하기 (Session id 없을 시 게시글 작성 안됨, alert login message) 
<img width="542" alt="스크린샷 2022-10-01 오후 2 30 43" src="https://user-images.githubusercontent.com/103010985/193394182-8439483b-f640-48c6-b4af-136fcd537ca2.png">

### 4.3 게시글 상세 보기 (Session 확인해서 id 일치 하지 않을 시, 수정 및 삭제 buttom이 안뜸)
<img width="1094" alt="스크린샷 2022-10-01 오후 2 29 32" src="https://user-images.githubusercontent.com/103010985/193394151-1540539b-01fd-4583-965c-3bb9aecb7ffd.png">


### 4.3.1 게시글 상세 보기 (Session 확인해서 id 일치할 경우)
<img width="1091" alt="스크린샷 2022-10-01 오후 2 27 36" src="https://user-images.githubusercontent.com/103010985/193394092-83f37533-a8aa-421a-8a7d-b02fcb93d064.png">


### 4.4 검색하기
<img width="1097" alt="스크린샷 2022-10-01 오후 2 32 31" src="https://user-images.githubusercontent.com/103010985/193394253-8ac5b603-8f0b-4ac5-9f71-210b2574f8bd.png">

### 4.5 수정하기
<img width="1095" alt="스크린샷 2022-10-01 오후 2 37 12" src="https://user-images.githubusercontent.com/103010985/193394399-b37edeef-bede-4e00-a532-7c954c49bb8b.png">


### 4.5 board Package
<img width="479" alt="스크린샷 2022-10-05 오후 5 08 11" src="https://user-images.githubusercontent.com/103010985/194011785-191d14ea-435e-4cba-8eb5-6d634d77b029.png">


### 4.6 Common Package
<img width="296" alt="스크린샷 2022-10-05 오후 5 10 40" src="https://user-images.githubusercontent.com/103010985/194012342-e86f1a17-6a50-4813-8538-6f4a0dc2d11d.png">


### 4.7 Swing view Package
<img width="934" alt="스크린샷 2022-09-28 오후 2 59 38" src="https://user-images.githubusercontent.com/103010985/192699678-a9633a72-cd2b-45aa-998d-453627fdcf7b.png">

# 5.기본 기능
- 등록 registered 
- 조회 listed
- 검색 searched
- 수정 modified
- 삭제 deleted



# 6.핵심 기능

### 6.1 게시글 등록, 수정 data 입력 받을 시 유효성 체크
<img width="456" alt="스크린샷 2022-09-28 오후 3 03 37" src="https://user-images.githubusercontent.com/103010985/192700162-accbc3ae-01e6-46b4-b306-fd52f68c60a6.png">


```java      
//등록 actionPerformed
@Override
public void actionPerformed(ActionEvent e) {

    //제목 유효성 검사
    userTitle = tittleArea.getText();
    if(!common.validation(Common.BOARD_TITLE, userTitle)) {
        while(true) {
            userTitle = JOptionPane.showInputDialog(null, "제목은 12글자 이하로 입력해야 합니다.\n다시 입력하세요.", "");

            if(common.validation(Common.BOARD_TITLE, userTitle)) {
                break;
            }
        }
    }
    userContent = contentArea.getText();

    //내용 유효성 검사
    if(!common.validation(Common.BOARD_CONTENT, userContent)) {
        while(true) {
            userContent = JOptionPane.showInputDialog(null, "내용은 200자 이하로 작성할 수 있습니다.\n글자수에 맞게 다시 작성하세요", "");

            if(common.validation(Common.BOARD_CONTENT, userContent)) {
                break;
            }
        }
    }
    userName = nameArea.getText();

    //이름 유효성 검사
    if(!common.validation(Common.BOARD_NAME, userName)) {
        while (true) {
            userName = JOptionPane.showInputDialog(null, "이름을 올바른 형식으로 입력하세요\n한글 및 영어만 가능합니다.", "");

            if(common.validation(Common.BOARD_NAME, userName)) {
                break;
            }
        }
    }

    try {
        int result = boardService.registered(userTitle, userContent, userName);

        if(result == 1) {
            JOptionPane.showMessageDialog(null, "등록이 완료되었습니다", "INFORMATION_MESSAGE", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } else {
            JOptionPane.showMessageDialog(null, "등록 실패하였습니다.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
        }
    } catch(SQLException ex) {
        ex.printStackTrace();
    }
    createdFrame.dispose();
    new View();
}
});
        
//수정 actionPerformed
@Override
public void actionPerformed(ActionEvent e) {
    Common common = new Common();

    //제목 유효성 검사
    userTitle = tittleArea.getText();
    if(!common.validation(Common.BOARD_TITLE, userTitle)) {
        while(true) {
            userTitle = JOptionPane.showInputDialog(null, "제목은 12글자 이하로 입력해야 합니다.\n다시 입력하세요.", "");

            if(common.validation(Common.BOARD_TITLE, userTitle)) {
                break;
            }
        }
    }
    userContent = contentArea.getText();

    //내용 유효성 검사
    if(!common.validation(Common.BOARD_CONTENT, userContent)) {
        while(true) {
            userContent = JOptionPane.showInputDialog(null, "내용은 200자 이하로 작성할 수 있습니다.\n글자수에 맞게 다시 작성하세요", "");

            if(common.validation(Common.BOARD_CONTENT, userContent)) {
                break;
            }
        }
    }
    userName = nameArea.getText();

    //이름 유효성 검사
    if(!common.validation(Common.BOARD_NAME, userName)) {
        while(true) {
            userName = JOptionPane.showInputDialog(null, "이름을 올바른 형식으로 입력하세요\n한글 및 영어만 가능합니다.", "");

            if(common.validation(Common.BOARD_NAME, userName)) {
                break;
            }
        }
    }
    try {
        int result = boardService.modified(num, userTitle, userContent, userName);

        if(result == 1) {
            JOptionPane.showMessageDialog(null, "수정이 완료되었습니다", "INFORMATION_MESSAGE", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            new View().revalidate();
            new View().repaint();
        } else {
            JOptionPane.showMessageDialog(null, "수정에 실패하였습니다.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            updatedFrame.dispose();
            new View().revalidate();
            new View().repaint();
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    setVisible(false);
}
```


# 7.회고

### Java_App_Board_SpringFramework_v7

1. Model View Controller, MVC1 디자인 패턴을 고려하여 Model은 Java Bean으로 구현하고 d/ JSP로 구현한 게시판 어플리케이션은 MVC Model1 구조로 웹 브라우저의 요청을 JSP페이지가 받아서
처리하는 구조이다. Model은 Java Bean이 처리를 하고, Controller와 Service가 명확히 구분이 되어 있지 않아 설계상 ~

2. html의 head, nav의 모듈화를 통해 JSP파일의 코드의 복잡성을 감소시켜 오류의 범위를 최소화 했습니다. 

3. JSP에서는 Cross site script에 대한 방지가 되어 있지 않아 특정 문자를 html entity code로 변환하여 출력 할 수 있도록 하였으며, 이때 recursion 형태로 함수를 구현하여 array, object, String의 타입으로 매개변수를 전달하여도 동작 할 수 있도록 구현 하였습니다.

4. 브라우저가 HTML을 전부 읽고 DOM 트리를 완성하는 즉시 발생시키는 DOMContentLoaded 이벤트를 활용하여 로그인시 null 값을 체크 할 수 있도록 구현하였습니다. button 클릭시, event.preventDefault();를 실행시켜 validation 함수를 호출 할 수 있도록 하였고, null값과 각 타입을 체크 할 수 있는 공통함수를 개발했습니다.





