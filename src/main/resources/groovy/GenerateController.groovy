//import com.intellij.database.model.DasTable
//import com.intellij.database.model.ObjectKind
//import com.intellij.database.util.Case
//import com.intellij.database.util.DasUtil
//
//
//packageName = "com.sample;"
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
//
//def generate(table, dir) {
//    def className = javaName(table.getName(), true)
//    def fields = calcFields(table)
//    new File(dir, className + "Controller.java").withPrintWriter { out -> generate(out, className, fields, table.getName()) }
//}
//
//def generate(out, className, fields, tableName) {
//
//    def camelClassName = Case.LOWER.apply(className.substring(0,1)) + className.substring(1)
//
//    out.println "package com.scrm.fintech.server.product.controller;"
//    out.println ""
//    out.println "import com.scrm.fintech.common.core.controller.BaseController;"
//    out.println "import com.scrm.fintech.common.vo.ResponseResult;"
//    out.println "import org.springframework.web.bind.annotation.*;"
//    out.println ""
//    out.println "@RestController"
//    out.println "@RequestMapping(\"/${Case.LOWER.apply(className)}\")"
//    out.println "public class ${underscoreToCamelCase(className)}Controller extends BaseController {"
//    out.println ""
//    out.println "  private ${underscoreToCamelCase(className)}Service ${camelClassName}Service;"
//    out.println ""
//    out.println "  public ${underscoreToCamelCase(className)}Controller(${underscoreToCamelCase(className)}Service service) {"
//    out.println "    this.${camelClassName}Service = service;"
//    out.println "  }"
//    out.println ""
//    out.println "  @RequestMapping(method = RequestMethod.GET)"
//    out.println "  public ResponseResult<Iterable<${underscoreToCamelCase(className)}Dto>> findAll() {"
//    out.println "    Iterable<${underscoreToCamelCase(className)}Dto> all${className} = this.${camelClassName}Service.findall(0, 100);"
//    out.println "    return Ok(all${className});"
//    out.println "  }"
//    out.println ""
//    out.println "  @RequestMapping(value = \"/{page}/{pageSize}\", method = RequestMethod.GET)"
//    out.println "  public ResponseResult<Iterable<${underscoreToCamelCase(className)}Dto>> findAll(@PathVariable(\"page\") final Integer page, @PathVariable(\"pageSize\") final Integer pageSize) {"
//    out.println "    Iterable<${underscoreToCamelCase(className)}Dto> all${className} = this.${camelClassName}Service.findall(page, pageSize);"
//    out.println "    return Ok(all${className});"
//    out.println "  }"
//    out.println ""
//    out.println "  @RequestMapping(value = \"/{id}\", method = RequestMethod.GET)"
//    out.println "  public ResponseResult<${underscoreToCamelCase(className)}Dto> get(@PathVariable(\"id\") final Long id) {"
//    out.println "    ${underscoreToCamelCase(className)}Dto entry = this.${camelClassName}Service.find(id);"
//    out.println "    return Ok(entry);"
//    out.println "  }"
//    out.println ""
//    out.println "  @RequestMapping(method = RequestMethod.POST)"
//    out.println "  public ResponseResult<${underscoreToCamelCase(className)}Dto> create(@RequestBody final ${underscoreToCamelCase(className)}Dto creating) {"
//    out.println "    ${underscoreToCamelCase(className)}Dto created = this.${camelClassName}Service.create(creating);"
//    out.println "    return Ok(created);"
//    out.println "  }"
//    out.println ""
//    out.println "  @RequestMapping(method = RequestMethod.PUT)"
//    out.println "  public ResponseResult<${underscoreToCamelCase(className)}Dto> update(@RequestBody final ${underscoreToCamelCase(className)}Dto updating) {"
//    out.println "    ${underscoreToCamelCase(className)}Dto updated = this.${camelClassName}Service.update(updating);"
//    out.println "    return Ok(updated);"
//    out.println "  }"
//    out.println ""
//    out.println "  @RequestMapping(value = \"/{id}\", method = RequestMethod.DELETE)"
//    out.println "  public ResponseResult delete(@PathVariable(\"id\") final Long id) {"
//    out.println "    this.${camelClassName}Service.delete(id);"
//    out.println "    return Ok();"
//    out.println "  }"
//    out.println "}"
//
//}
//
//def calcFields(table) {
//    DasUtil.getColumns(table).reduce([]) { fields, col ->
//        def spec = Case.LOWER.apply(col.getDataType().getSpecification())
//        def typeStr = typeMapping.find { p, t -> p.matcher(spec).find() }.value
//        fields += [[
//                           name : javaName(col.getName(), false),
//                           type : typeStr,
//                           annos: "",
//                           colName: col.getName(),
//                           primary: DasUtil.isPrimary(col)
//                   ]]
//    }
//}
//
//def javaName(str, capitalize) {
//    def s = com.intellij.psi.codeStyle.NameUtil.splitNameIntoWords(str)
//            .collect { Case.LOWER.apply(it).capitalize() }
//            .join("")
//            .replaceAll(/[^\p{javaJavaIdentifierPart}[_]]/, "_")
//    capitalize || s.length() == 1? s : Case.LOWER.apply(s[0]) + s[1..-1]
//}
//
//String underscoreToCamelCase(String underscore){
//    if(!underscore || underscore.isAllWhitespace()){
//        return ''
//    }
//    return underscore.replaceAll(/_\w/){ it[1].toUpperCase() }
//}
