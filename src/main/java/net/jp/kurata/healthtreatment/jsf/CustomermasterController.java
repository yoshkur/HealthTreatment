package net.jp.kurata.healthtreatment.jsf;

import net.jp.kurata.healthtreatment.entity.Customermaster;
import net.jp.kurata.healthtreatment.jsf.util.JsfUtil;
import net.jp.kurata.healthtreatment.jsf.util.PaginationHelper;
import net.jp.kurata.healthtreatment.ejb.CustomermasterFacade;

import java.io.Serializable;
import java.util.Date;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import net.jp.kurata.healthtreatment.entity.Treatment_;
import net.jp.kurata.healthtreatment.jsf.customermaster.CustomerMasterSearchCondition;
import net.jp.kurata.healthtreatment.jsf.treatment.TreatmentSearchCondition;

@Named("customermasterController")
@SessionScoped
public class CustomermasterController implements Serializable {

    private Customermaster current;
    private DataModel items = null;
    @EJB
    private net.jp.kurata.healthtreatment.ejb.CustomermasterFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private CustomerMasterSearchCondition condition;
    private Integer pageSize = 20;
    @Inject
    private TreatmentController treatmentController;

    public CustomermasterController() {
    }

    public Customermaster getSelected() {
        if (current == null) {
            current = new Customermaster();
            selectedItemIndex = -1;
        }
        return current;
    }

    private CustomermasterFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(getPageSize()) {

                @Override
                public int getItemsCount() {
//                    return getFacade().count();
                    return getFacade().countRequest(getCondition());
                }

                @Override
                public DataModel createPageDataModel() {
//                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                    return new ListDataModel(getFacade().findRequestRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}, getCondition()));
                }
            };
        }
        return pagination;
    }

    public CustomerMasterSearchCondition getCondition() {
        if (this.condition == null) {
            this.condition = new CustomerMasterSearchCondition();
        }
        return condition;
    }

    public void setCondition(CustomerMasterSearchCondition condition) {
        this.condition = condition;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String prepareList() {
        recreateModel();
        return "/customermaster/List?faces-redirect=true";
    }

    public String prepareView() {
        current = (Customermaster) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        this.treatmentController.prepareList();
        return "/customermaster/View?faces-redirect=true";
    }

    public String prepareCreate() {
        current = new Customermaster();
        selectedItemIndex = -1;
        return "/customermaster/Create?faces-redirect=true";
    }

    public String create() {
        this.getSelected().setRecorddate(new Date());
        this.getSelected().setRecordprogram(this.getClass().getName());
        this.getSelected().setRecorduserid("admin");
        this.getSelected().setRecordvalid(true);
        this.getSelected().setCustomerinformationid(this.getSelected().getId());
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CustomermasterCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Customermaster) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "/customermaster/Edit?faces-redirect=true";
    }

    public String update() {
        this.getSelected().setRecorddate(new Date());
        this.getSelected().setRecordprogram(this.getClass().getName());
        this.getSelected().setRecorduserid("admin");
        this.getSelected().setRecordvalid(true);
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CustomermasterUpdated"));
            return "/customermaster/View?faces-redirect=true";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Customermaster) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "/customermaster/List?faces-redirect=true";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "/customermaster/View?faces-redirect=true";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "/customermaster/List?faces-redirect=true";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CustomermasterDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "/customermaster/List?faces-redirect=true";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "/customermaster/List?faces-redirect=true";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public Customermaster getCustomermaster(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Customermaster.class)
    public static class CustomermasterControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CustomermasterController controller = (CustomermasterController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "customermasterController");
            return controller.getCustomermaster(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Customermaster) {
                Customermaster o = (Customermaster) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Customermaster.class.getName());
            }
        }

    }

}
