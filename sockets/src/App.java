public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }
}

// public class App{
       
//     public static void main(String[] args) {
         
//         TextFile file = new TextFile("content.txt", "Hello World");
//         printFileName(file);
//         printObj(file);
//     }
 
//     static void printFileName(File file){
//         System.out.println("File name: " + file.getName());
//     }
 
//     static void printObj(Printable obj){
//         obj.print();
//     }
// }

// interface File{
//     String getName();  // возвращает имя файла
// }
// interface Printable{
//     void print();  // печать содержимого
// }
// class TextFile implements File, Printable{
 
//     private String name;
//     private String text;
 
//     TextFile(String name, String text){
 
//         this.name = name;
//         this.text = text;
//     }
//     public String getName(){ return this.name; }
//     public void print() { System.out.println(text); }
// }
