package support

import geb.Browser
import geb.binding.BindingUpdater
import org.codehaus.groovy.grails.test.support.GrailsTestRequestEnvironmentInterceptor
import residuosquimicos.Laboratorio
import residuosquimicos.Residuo
import residuosquimicos.Usuario

this.metaClass.mixin(cucumber.api.groovy.Hooks)

Before() {
    bindingUpdater = new BindingUpdater(binding, new Browser())
    bindingUpdater.initialize()

    scenarioInterceptor = new GrailsTestRequestEnvironmentInterceptor(appCtx)
    scenarioInterceptor.init()
}

After() {
    scenarioInterceptor.destroy()
    //Usuario.all.each {
    //    it.delete()
    //}
    bindingUpdater.remove()
    Residuo.executeUpdate('delete from Residuo')
    Laboratorio.executeUpdate('delete from Laboratorio')

    Usuario.all.each {
        it.delete()
    }
}