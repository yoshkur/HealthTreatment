package net.jp.kurata.healthtreatment.jsf;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import net.jp.kurata.healthtreatment.entity.Treatment;
import net.jp.kurata.healthtreatment.jsf.util.JsfUtil;
import net.jp.kurata.healthtreatment.jsf.util.PaginationHelper;
import net.jp.kurata.healthtreatment.ejb.TreatmentFacade;

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
import javax.servlet.http.Part;
import net.jp.kurata.healthtreatment.entity.Treatment_;
import net.jp.kurata.healthtreatment.jsf.treatment.TreatmentSearchCondition;

@Named("treatmentController")
@SessionScoped
public class TreatmentController implements Serializable {

    private Treatment current;
    private DataModel items = null;
    @EJB
    private net.jp.kurata.healthtreatment.ejb.TreatmentFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private Part attachedFile;
    @Inject
    private CustomermasterController customerMaster;
    private TreatmentSearchCondition condition;
    private Integer pageSize = 20;
    @Inject
    private TreatmentattachedfileController treatmentattachedfileController;

    public TreatmentController() {
    }

    public Treatment getSelected() {
        if (current == null) {
            current = new Treatment();
            selectedItemIndex = -1;
        }
        return current;
    }

    private TreatmentFacade getFacade() {
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

    public TreatmentSearchCondition getCondition() {
        if (this.condition == null) {
            this.condition = new TreatmentSearchCondition();
        }
        return condition;
    }

    public void setCondition(TreatmentSearchCondition condition) {
        this.condition = condition;
    }

    public Part getAttachedFile() {
        return attachedFile;
    }

    public void setAttachedFile(Part attachedFile) {
        this.attachedFile = attachedFile;
        if (this.attachedFile != null) {
            this.getSelected().setAttachedfile(Boolean.TRUE);
            this.getSelected().setAttachedfilename(this.attachedFile.getSubmittedFileName());
            this.convertFileField();
        } else {
            this.getSelected().setAttachedfile(Boolean.FALSE);
            this.getSelected().setAttachedfilename(null);
            this.treatmentattachedfileController.destroy();
        }
    }

    private void convertFileField() {
        int BUFFER_SIZE = 1024;
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        try {
            InputStream inputStream = this.attachedFile.getInputStream();
            byte[] buffer = new byte[BUFFER_SIZE];
            for (int len = inputStream.read(buffer); len >= 0; len = inputStream.read(buffer)) {
                bout.write(buffer, 0, len);
            }
        } catch (IOException e) {

        } finally {

        }
        this.treatmentattachedfileController.getSelected().setAttachedfiledata(bout.toByteArray());
        this.treatmentattachedfileController.getSelected().setAttachedfilename(this.attachedFile.getSubmittedFileName());

    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String prepareList() {
        this.getCondition().setCustomerid(this.customerMaster.getSelected().getId());
        this.getCondition().setOrderBy(Treatment_.treatmentdate);
        this.getCondition().setAsc(Boolean.FALSE);
        recreateModel();
        return "/customermaster/View?faces-redirect=true";
    }

    public String prepareView() {
        current = (Treatment) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "/treatment/View?faces-redirect=true";
    }

    public String prepareCreate() {
        current = new Treatment();
        selectedItemIndex = -1;
        return "/treatment/Create?faces-redirect=true";
    }

    public String create() {
        this.getSelected().setRecorddate(new Date());
        this.getSelected().setRecordprogram(this.getClass().getName());
        this.getSelected().setRecorduserid("admin");
        this.getSelected().setRecordvalid(true);
        this.getSelected().setCustomerid(this.customerMaster.getSelected().getId());
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TreatmentCreated"));
            if (this.getSelected().getAttachedfile()) {
                this.treatmentattachedfileController.create();
            }
            return prepareList();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Treatment) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "/treatment/Edit?faces-redirect=true";
    }

    public String update() {
        this.getSelected().setRecorddate(new Date());
        this.getSelected().setRecordprogram(this.getClass().getName());
        this.getSelected().setRecorduserid("admin");
        this.getSelected().setRecordvalid(true);
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TreatmentUpdated"));
            return "/treatment/View?faces-redirect=true";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Treatment) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "/customermaster/View?faces-redirect=true";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "/treatment/View?faces-redirect=true";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "/customermaster/View?faces-redirect=true";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TreatmentDeleted"));
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
        return "/customermaster/View?faces-redirect=true";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "/customermaster/View?faces-redirect=true";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public Treatment getTreatment(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Treatment.class)
    public static class TreatmentControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TreatmentController controller = (TreatmentController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "treatmentController");
            return controller.getTreatment(getKey(value));
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
            if (object instanceof Treatment) {
                Treatment o = (Treatment) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Treatment.class.getName());
            }
        }

    }

}
