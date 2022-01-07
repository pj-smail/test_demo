//import com.intellij.database.model.DasTable
//import com.intellij.database.model.ObjectKind
//import com.intellij.database.util.Case
//import com.intellij.database.util.DasUtil
//
//packageName = ""
//typeMapping = [
//        (~/(?i)tinyint|smallint|mediumint|int/)      : "Integer",
//        (~/(?i)float|double|decimal|real/)       : "Double",
//        (~/(?i)decimal/)                  : "BigDecimal",
//        (~/(?i)datetime|timestamp/)       : "Date",
//        (~/(?i)date/)                     : "Date",
//        (~/(?i)/)                         : "String"
//]
//
//FILES.chooseDirectoryAndSave("Choose directory", "Choose where to store generated files") { dir ->
//    SELECTION.filter { it instanceof DasTable && it.getKind() == ObjectKind.TABLE }.each { generate(it, dir) }
//}
///**
// * 创建对应的pojo Java文件
// * @param table
// * @param dir
// * @return
// */
//def generate(table, dir) {
//    def className = javaName(table.getName(), true)
//    className = className + "Dto"
//    def fields = calcFields(table)
//    packageName = getPackageName(dir)
//    new File(dir, className + ".java").withPrintWriter('UTF-8') { out -> generate(out, className, fields, table) }
//}
//
///**
// * 实体类代码生成
// * @param out
// * @param className
// * @param fields
// * @return
// */
//def generate(out, className, fields, table) {
//    out.println "package $packageName"
//    out.println ""
//    out.println "import java.io.Serializable;"
//    out.println "import java.util.Date;"
//    out.println "import lombok.Data;"
//    out.println "import io.swagger.annotations.ApiModelProperty;"
//    out.println ""
//    out.println "@Data"
//    out.println "public class $className implements Serializable {"
//    out.println ""
//    out.println genSerialID()
//    fields.each() {
//        out.println ""
//        // 输出注释
//        out.println "\t@ApiModelProperty(\"${it.commoent.toString()}\")"
//        out.println "\tprivate ${it.type} ${it.name};"
//    }
//    out.println ""
//    out.println "}"
//}
///**
// * 获取包名称
// * @param dir 实体类所在目录
// * @return
// */
//def getPackageName(dir) {
//    return dir.toString().replaceAll("/", ".").replaceAll("\\\\", ".").replaceAll("^.*src(\\.main\\.java\\.)?", "") + ";"
//}
//
//def calcFields(table) {
//    DasUtil.getColumns(table).reduce([]) { fields, col ->
//        def spec = Case.LOWER.apply(col.getDataType().getSpecification())
//        def typeStr = typeMapping.find { p, t -> p.matcher(spec).find() }.value
//
//        def comm = [
//                colName : col.getName(),
//                name    : javaName(col.getName(), false),
//                type    : typeStr,
//                commoent: col.getComment(),
//                annos   : ""
//        ]
//        if ("id".equals(Case.LOWER.apply(col.getName())))
//            comm.annos = "\t@TableId(value = \"id\", type = IdType.INPUT)"
//        fields += [comm]
//    }
//}
//
//def javaName(str, capitalize) {
//    def s = str.split(/[^\p{Alnum}]/).collect { def s = Case.LOWER.apply(it).capitalize() }.join("")
//    capitalize ? s : Case.LOWER.apply(s[0]) + s[1..-1]
//}
//
//static String genSerialID()
//{
//    return "  private static final long serialVersionUID =  "+Math.abs(new Random().nextLong())+"L;";
//}
//
//
