package day0110;
// 사원 관리 프로그램을 작성하시오.
// 단, 사원 정보(사원 번호, 이름, 직급, 소속 부서, 연봉)은 하나의 구조체로 통제하고
// 사원 정보 입력, 출력은 별개의 메소드를 통하여 관리합니다.

import util.ScannerUtil;

import java.util.Scanner;

public class Ex03Map {
    public static void main(String[] args) {
        Employee emp = new Employee();  // 참조형 변수

        Scanner scanner = new Scanner(System.in);

        insertInfo(scanner, emp);  // call by ref

        printInfo(emp);

        scanner.close();
    }
    public static void insertInfo(Scanner scanner, Employee employee) {
        // 각종 출력에서 사용할 메시지를 저장할 String 변수 message
        String message;

        // 사원 번호 입력
        message = "사원의 번호를 입력해주세요";
        employee.id = ScannerUtil.nextInt(scanner, message);

        // 사원 이름 입력
        message = "사원의 이름을 입력해주세요";
        employee.name = ScannerUtil.nextLine(scanner, message);

        // 사원 직급 입력
        message = "사원의 직급을 입력해주세요";
        employee.position = ScannerUtil.nextLine(scanner, message);

        // 사원 부서 입력
        message = "사원의 부서를 입력해주세요";
        employee.department = ScannerUtil.nextLine(scanner, message);

        // 사원 연봉 입력
        message = "사원의 연봉을 입력해주세요";
        employee.salary = ScannerUtil.nextInt(scanner, message);
    }
    public static void printInfo(Employee e) {
        System.out.println("사원 번호 : " + e.id);
        System.out.println("사원 이름 : " + e.name);
        System.out.println("사원 직급 : " + e.position);
        System.out.println("사원 부서 : " + e.department);
        System.out.println("사원 연봉 : " + e.salary);
    }
}


