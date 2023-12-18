# Web Application Development with Java Servlet, JSP, and JPA

## Table of Contents

1. [Development](#development)
2. [Additional Resources](#additional-resources)

## Set up the project by Guy K.
https://tall-shamrock-65b.notion.site/Set-up-Your-Project-for-JPA-c426b5af616b4b0bbb4de58713b865b6

## Development

## 1. Implement Servlets in `controllers package`.
```java
@WebServlet(name = "ชื่อ servlet", value = "/path ที่เอาไว้เรียกใช้ servlet")
public class MyServletClass extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // ทำงานใน doGet ถ้า request มาผ่าน URL path
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // ทำงานใน doPost ถ้า request มาผ่าน <form method="post">
    }
}
```

- ### **request.getPrameter() / request.setAttribute()**
```java
// ดึงค่าใน parameter "name" ซึ่งจะ return เป็น String เสมอ
String name = request.getParameter("name");

// Cast String ให้เป็น type ที่ต้องการ
Employee em = (Employee) request.getParameter("em");
Integer salary = Interger.parseInt(request.getParameter("parameter"));

// set attribute ให้กับ request (สามารถ set ให้ response ได้เหมือนกัน)
request.setAttribute("key ของ attribute", ค่าที่ต้องการ set);
```

- ### **วิธีใช้ Repository ใน Servlet**
```java
// สร้าง Object ของ Repository มาเก็บไว้ก่อน
EmployeeRepository emRepo = new EmployeeRepository();

// เรียกใช้ method ใน repo ได้เลย เช่น findById(id)
Employee em = emRepo.findById(ค่าที่รับของเมธอด);
```

- ### **วิธีใช้ HttpSession**
```java
// สร้าง Session หรือ ดึงตัวที่มีอยู่แล้วมาผ่าน request
// ถ้า session เคยถูกสร้างใน request นั้นแล้ว จะ return session เดิมให้เลย ถ้าไม่ จะสร้าง session ใหม่และ return
HttpSession session = request.getSession();

// ใส่ parameter false
// ดึง session ที่มีอยู่แล้วมาผ่าน request
// ถ้าไม่เคยถูกสร้าง จะ return null
HttpSession session = request.getSession(false);

// set attribute ให้ session โดยใช้ setAttribute
session.setAttribute("key ของ attribute", ค่าที่ต้องการ set);
```

- ### **ส่ง request หรือ response ไปทำงานต่อ**
```java
// ส่ง request และ response ไปทำงานใน /path ที่กำหนด
// โดย request.getRequestDispatcher จะอ้างถึง root path ของเรา เช่น http://localhost:8080/project_name_war_exploded/
request.getRequestDispatcher("/path").forward(request, response);

// ส่ง response ไปที่ path ที่กำหนด (เป็น absolute path)
// โดยมักใช้คู่กับ request.getContextPath() ซึ่งจะ return root path
// กรณีนี้คือต้องการกลับไปหน้าแรก
response.sendRedirect(request.getContextPath());

// แต่ถ้าอยากอ้างถึง path ของ servlet หรือ jsp ให้ใช้วิธี concat
response.sendRedirect(request.getContextPath() + "/path");
```
---
## 2. Implement JSP in `webapp package`.

```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
    <link สำหรับ styling CSS>
    <style>หรือเขียน CSS ใน tag นี้</style>
</head>
<body>
    <!-- content ทั้งหมดใน page -->
</body>
</html>
```

- ### **JSTL พื้นฐาน**
```jsp
<!-- เรียกใช้ค่าจาก Servlet ที่ส่งมา โดย key attribute -->
<!-- pathToGo และ textToDisplay ถูก set ไว้ตอน setAttribute -->
<a href="${pathToGo}">${textToDisplay}</a>


<!-- ถ้าชื่อของ attribute ใน request และ session ซํ้ากัน -->
<!-- ใช้การเรียกผ่าน Scope -->
<h3>Login by: ${sessionScope.name}</h3>
<p>Your fullname: ${requestScope.name}</p>


<!-- ใช้ forEach loop เพื่อแสดงข้อมูลใน List -->
<!-- items อ้างถึง List ที่ต้องการ loop -->
<c:forEach items="${employees}" var="em" varStatus="vs">
    <!-- var อ้างถึง element แต่ละตัว -->
    <h2>${em.name}</h2>

    <!-- varStatus ใช้บอก status ของ element ใน loop -->
    ${vs.index} index (เริ่มจาก 0)
    ${vs.count} รอบที่ loop (เริ่มจาก 1)
    ${vs.first} return true ถ้าเป็น loop แรก
    ${vs.last} return true ถ้าเป็น loop ท้าย
</c:forEach>

<!-- if-else ใน JSP -->
<c:choose>
    <!-- แสดง name ถ้า name ไม่เป็นค่าว่าง (not empty) -->
    <!-- ใน test ต้อง return boolean -->
    <c:choose test="${not empty name}">
        <p>${name}</p>
    </c:choose>
    <!-- else ถ้าไม่ตรงตาม condition ใน test -->
    <c:otherwise>
        <p>Do not have name yet</p>
    </c:otherwise>
</c:choose>

<!-- if อย่างเดียว ไม่มี else -->
<c:if test="${empty name}">
    <!-- ถ้า name เป็นค่าว่าง ให้แสดงตามนี้ -->
    <p>Do not have name yet</p>
</c:if>
```
---
## 3. Define JPA entities in `models package`.
```java
// Annotations ของ lombok และ JPA
@Getter 
@Setter
@ToString
@Entity
@Table(name = "myTableName")
// :id เป็นค่าที่ถูก set ผ่าน Query ใน Repository
@NamedQueries({
        @NamedQuery(name = "MyClass.queryMethod1", query = "SQL query statement"),
        @NamedQuery(name = "MyClass.queryMethod2", query = "select a from MyClass a where a.id = :id"),
})
public class MyClass {
    // @Id บนตัวแปรที่เป็น primary key ของตาราง
    @Id
    private String id;

    // @Column ถ้าชื่อของตัวแปรไม่ตรงกับชื่อคอลัมน์ในตาราง
    @Column(name = "columnName")
    private String name;
    private String DOB;

    // ตัวแปรประเภท List ใช้ @OneToMany mappedBy ตัวแปรของอีกตารางที่ใช้เชื่อมกับ Entity ตัวนี้
    @OneToMany(mappedBy = "super")
    private List<MySubClass> subs;
}
```
```java
@Entity
@Table(name = "myTableName")
public class MySubClass {
    @Id
    private String id;
    @Column(name = "columnName")
    private String name;
    private String DOB;

    // ตัวแปรที่อ้างถึง Object ที่ map กับ List<MySubClass>
    // ใช้ @ManyToOne และ @JoinColumn(name = "id")
    // id คือชื่อตัวแปรที่เป็น primary key
    @ManyToOne
    @JoinColumn(name = "id")
    private MyClass super;
}
```
---
## 4. Create Repositories in `repositories package` for database interactions.
- ### กดดูเพิ่มเติมสำหรับการใช้ Query
- https://docs.oracle.com/javaee%2F6%2Fapi%2F%2F/javax/persistence/Query.html
```java
public class MyClassRepository {
    private EntityManager em;

    public EntityManager getEntityManager() {
        if (em == null || !em.isOpen()) {
            em = EntityManagerBuilder.getEntityManager();
        }
        return em;
    }

    public List<MyClass> findAll() {
        // สร้าง Query ที่เคยประกาศไว้ใน Entity class โดยใช้ @NamedQuery ผ่าน EntityManager
        // getEntityManager() return EntityManager
        // createNamedQuery("ชื่อ query ใน attribute name", MyClass.class)
        // getResultList() return List ของ class ที่ถูก query
        return getEntityManager().createNamedQuery("MyClass.findAll", MyClass.class).getResultList();
    }

    public MyClass findById(Integer id) {
        // set parameter id เพื่อใช้ใน @NamedQuery() ':id'
        // getSingleResult() ใช้กับ query ที่ return object เพียงตัวเดียว
        return getEntityManager()
                .createNamedQuery("MyClass.findById", MyClass.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public void create(MyClass obj) {
        EntityManager em = getEntityManager();
        try {
            // เพิ่ม obj ที่รับเข้าลง database
            em.getTransaction().begin();
            em.persist(obj);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            // ถ้าเกิด error ให้ rollback กลับ
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        }
    }
}
```
---
## Additional Resources

- [JSP Documentation](https://docs.oracle.com/javaee/5/tutorial/doc/bnaou.html)
- [JPA Documentation](https://docs.oracle.com/javaee/7/tutorial/persistence-intro.htm)
- [carrynong2's GitHub Repository](https://github.com/carrynong2/servlet-jsp)
- [Guy K's Server-Side Notion](https://tall-shamrock-65b.notion.site/Server-Side-350d38886cff448388b801e4fe86a824)
