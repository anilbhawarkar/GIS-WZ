package com.anilscript.GetAPIwz.repository;

import com.anilscript.GetAPIwz.model.*;
import org.geolatte.geom.M;
import org.hibernate.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

import static java.lang.Long.getLong;
import static javax.swing.UIManager.getInt;
import static javax.swing.UIManager.getString;

@Repository
public class RapdrpRepository {
    @Autowired
    private JdbcTemplate jdbcTemplateObject;

    public List<Dashboard> getDashboardSearch(RapdrpLocationModel Search) {
        List<Dashboard> dashboardList = new ArrayList<>();
        //System.out.println(Search);
        try {
            String region = Search.getCode_of_region();
            String circle = Search.getCode_of_circle();
            String division = Search.getCode_of_division();
            String dc = Search.getCode_of_distribution_center();
            String sql = "select * from electric.getdashboard_search('" + region +"','" + circle + "','"+ division + "','"+dc+"')";
            System.out.println(sql);
            List<Map<String, Object>> _stats = jdbcTemplateObject.queryForList(sql);
            System.out.println(_stats.size());
            System.out.println(_stats);
            if(_stats.size() >0)
            {
                for(Map row : _stats)
                {
                    //System.out.println(row.toString());
                    Dashboard obj = new Dashboard();
                    obj.setFeeder_11kv_count((String)row.get("feeder_11kv_count"));
                    obj.setFeeder_33kv_count((String) row.get("feeder_33kv_count"));
                    obj.setSs_33_11kv_count((String) row.get("ss_33_11kv_count"));
                    obj.setLength_33kv_existing((String) row.get("ss_33_11kv_count"));
                    obj.setLength_33kv_proposed((String) row.get("length_33kv_proposed"));
                    obj.setLength_11kv_existing((String) row.get("length_11kv_existing"));
                    obj.setLength_11kv_proposed((String) row.get("length_11kv_proposed"));
                    obj.setLength_lt_existing((String) row.get("length_lt_existing"));
                    obj.setLength_lt_proposed((String) row.get("length_lt_proposed"));
                    obj.setLength_ul_11kv_existing((String) row.get("length_ul_11kv_existing"));
                    obj.setLength_ul_11kv_proposed((String)row.get("length_ul_11kv_proposed"));
                    obj.setLength_ul_33kv_existing((String) row.get("length_ul_33kv_existing"));
                    obj.setLength_ul_33kv_proposed((String) row.get("length_ul_33kv_proposed"));
                    obj.setLength_ul_ltkv_existing((String) row.get("length_ul_ltkv_existing"));
                    obj.setLength_ul_ltkv_proposed((String) row.get("length_ul_ltkv_proposed"));
                    obj.setTotal_dtr((String) row.get("total_dtr"));
                    obj.setJob_pending_for_approval((String) row.get("job_pending_for_approval"));
                    obj.setJob_assigned((String) row.get("job_assigned"));
                    obj.setJob_completed((String) row.get("job_completed"));
                    obj.setJob_created((String) row.get("job_created"));
                    obj.setJob_started_execution((String) row.get("job_started_execution"));
                    dashboardList.add(obj);
                    //System.out.println(obj.feeder_11kv_count);
                }
            }
            //System.out.println(dashboardList.size());
        } catch (TypeMismatchException e) {
            throw new IllegalArgumentException(e);
        } catch (Exception ex) {
            throw ex;
        } finally {
            return dashboardList;
        }
    }

    public List<FeederDtrCapacity> getDtrCapacityWiseCount(RapdrpLocationModel feederSearch) {
        List<FeederDtrCapacity> dashboardList = new ArrayList<>();
        try
        {
            String region = feederSearch.getCode_of_region();
            String circle = feederSearch.getCode_of_circle();
            String division = feederSearch.getCode_of_division();
            String dc = feederSearch.getCode_of_distribution_center();
            String url = "select * from electric.getdtrcapacitywisecount('" +region + "','" +circle+ "','" +division+"','"+dc+ "')";
            //System.out.println(feederSearch);
            System.out.println(url);
            int total = 0;
            List<Map<String, Object>> _stats = jdbcTemplateObject.queryForList(url);
            System.out.println("data size"+_stats.size());
            if(_stats.size() >0)
            {
                for(Map row: _stats)
                {
                    System.out.println(row);
                    FeederDtrCapacity obj = new FeederDtrCapacity();
                    obj.setName((String) row.get("_name_"));
                    obj.setValue((String) row.get("_value_"));
                    dashboardList.add(obj);
                    System.out.println(obj.toString());
                }
            }

        }
        catch (TypeMismatchException e) {
            throw new IllegalArgumentException(e);
        } catch (Exception ex) {
            throw ex;
        } finally {
            return dashboardList;
        }
    }

    public List<MpSubStation> getSubStation(RapdrpLocationModel feederSearch) {
        List<MpSubStation> mpSubStationsList = new ArrayList<>();
        try
        {
            String region = feederSearch.getCode_of_region();
            String circle = feederSearch.getCode_of_circle();
            String division = feederSearch.getCode_of_division();

            //String url = "select * from auth.mpsubstation where division_code = '"+division+"' OR circle_code='"+circle+"' OR   region = '"+region+"'";
            String url = "select * from auth.getsubstation('" +region + "','" +circle+ "','" +division+"')";
            //System.out.println(feederSearch);
            System.out.println(url);
//            int total = 0;
               List<Map<String, Object>> _stats = jdbcTemplateObject.queryForList(url);
               System.out.println("data size"+_stats.size());
            if(_stats.size() >0)
            {
                for(Map row: _stats)
                {
                    //System.out.println(row);
                    MpSubStation obj = new MpSubStation();
                    obj.setRegionName((String)row.get("region_name"));
                    obj.setRegionCode((String)row.get("region"));
                    obj.setCircleName((String)row.get("circle_name"));
                    obj.setCircleCode((String) row.get("circle_code"));
                    obj.setDivisionName((String) row.get("division_name"));
                    obj.setDivisionCode((String) row.get("division_code"));
                    obj.setSubStationCode((String)row.get("sub_station_code"));
                    obj.setSubStationName((String) row.get("sub_station_name"));
                    obj.setLatitude((String) row.get("latitude"));
                    obj.setLongitude((String) row.get("longitude"));
                    mpSubStationsList.add(obj);
                    //System.out.println("obj value = "+obj.toString());
                }
            }

        }
        catch (TypeMismatchException e) {
            throw new IllegalArgumentException(e);
        } catch (Exception ex) {
            throw ex;
        } finally {
            return mpSubStationsList;
        }

    }

    public List<FeederSurveyCountModel> getFeederCountDashboard_11kv(SurveySearchModel feederSearch) {
        List<FeederSurveyCountModel> feederList = new ArrayList<>();
        try {
            String region = feederSearch.getCode_of_region();
            String circle = feederSearch.getCode_of_circle();
            String div = feederSearch.getCode_of_division();
            String dc = feederSearch.getCode_of_distribution_center();
            List<Map<String, Object>> _stats = jdbcTemplateObject.queryForList(" select * from electric.get_11kv_feeder_count_dashboard('" + region + "','" + circle + "','" + div + "','" + dc + "')");
            if (_stats.size() > 0) {
                for (Map row : _stats) {
                    FeederSurveyCountModel fMaster = new FeederSurveyCountModel();
                    fMaster.setKv_11_circle_name(String.valueOf(row.get("kv_11_circle_name")));
                    fMaster.setKv_11_division_name(String.valueOf(row.get("kv_11_division_name")));
                    fMaster.setKv_11_33_11_ss_name(String.valueOf(row.get("kv_11_33_11_ss_name")));
                    fMaster.setKv_11_feeder_code(String.valueOf(row.get("kv_11_feeder_code")));
                    fMaster.setKv_11_feeder_name(String.valueOf(row.get("kv_11_feeder_name")));
                    fMaster.setKv_11_total_no_of_dtr(String.valueOf(row.get("kv_11_total_no_of_dtr")));
                    fMaster.setKv_11_line_loss_p(String.valueOf(row.get("kv_11_line_loss_p")));
                    fMaster.setTotal_dtr_capacity_in_kva(String.valueOf(row.get("total_dtr_capacity_in_kva")));
                    double feLength_existing = row.get("kv_11_feeder_length_in_kms_existing") != null && row.get("kv_11_feeder_length_in_kms_existing") != "" ? Double.parseDouble(String.valueOf(row.get("kv_11_feeder_length_in_kms_existing"))) : 0;
                    double feLength_proposed = row.get("kv_11_feeder_length_in_kms_proposed") != null && row.get("kv_11_feeder_length_in_kms_proposed") != "" ? Double.parseDouble(String.valueOf(row.get("kv_11_feeder_length_in_kms_proposed"))) : 0;
                    DecimalFormat dFormat = new DecimalFormat("##.00");
                    fMaster.setKv_11_feeder_length_in_kms_existing(String.valueOf(dFormat.format(feLength_existing)));
                    fMaster.setKv_11_feeder_length_in_kms_proposed(String.valueOf(dFormat.format(feLength_proposed)));
                    fMaster.setKv_11_p_voltage_regulations_vr(String.valueOf(row.get("kv_11_p_voltage_regulations_vr")));
                    fMaster.setKv_11_line_loss_in_kw(String.valueOf(row.get("kv_11_line_loss_in_kw")));
                    fMaster.setCode_of_circle(String.valueOf(row.get("code_of_circle")));
                    fMaster.setCode_of_distribution_center(String.valueOf(row.get("code_of_distribution_center")));
                    fMaster.setCode_of_division(String.valueOf(row.get("code_of_division")));
                    fMaster.setCode_of_feeder(String.valueOf(row.get("code_of_feeder")));
                    fMaster.setCode_of_region(String.valueOf(row.get("code_of_region")));
                    fMaster.setType_of_feeder(String.valueOf(row.get("type_of_feeder")));
                    fMaster.setConsumer_count(String.valueOf(row.get("consumer_count")));
                    feederList.add(fMaster);
                }
            }
        } catch (TypeMismatchException e) {
            throw new IllegalArgumentException(e);
        } catch (Exception ex) {
            throw ex;
        } finally {
            return feederList;
        }
    }


    public Object   getRapdrpDTRMapview(RapdrpLocationModel model) {
        String region = model.getCode_of_region();
        String circle = model.getCode_of_circle();
        String div = model.getCode_of_division();
        String dc = model.getCode_of_distribution_center();
        String feeder_code = model.getCode_of_feeder();
        String feeder_type = model.getType_of_feeder();

        String sql = "select * from electric.get_dtr_11_04_kv('" + region + "','" + circle + "','"+div+ "','" + dc + "','" + feeder_code +"','"+ feeder_type + "')";
        System.out.println(sql);
        List<Map<String, Object>> _feeder = jdbcTemplateObject.queryForList(sql);
        return _feeder;

    }

    public Object getRapdrpPole11kvMapview(RapdrpLocationModel model) {
        System.out.println(model.toString());
        String region = model.getCode_of_region();
        String circle = model.getCode_of_circle();
        String div = model.getCode_of_division();
        String dc = model.getCode_of_distribution_center();
        String feeder_code = model.getCode_of_feeder();
        String feeder_type = model.getType_of_feeder();
        String Sql = " select * from electric.get_pole_11_kv('" + region + "','" + circle + "','" + div + "','" + dc + "','" + feeder_code + "'," + feeder_type.toString() + ")";
        System.out.println(Sql);
        List<Map<String, Object>> _feeders = jdbcTemplateObject.queryForList(Sql);
        return _feeders;
    }

    public Object updateEstimateNo(UpdateEstimateModel model) {
        System.out.println("In Repo");
       System.out.println("Pole_code = "+model.getPole_code());
        System.out.println("Estimate Number="+model.getEstimateno());
        String estimateno = model.getEstimateno();
        String poleCode = model.getPole_code();
//        String Sql = "UPDATE electric.support_structure SET estimateno = '123' WHERE pole_code = '1623297587-221'";
        String Sql = "UPDATE electric.support_structure SET estimateno = '"+estimateno+"' WHERE pole_code = '"+poleCode+"'";
        System.out.println(Sql);
        int status = jdbcTemplateObject.update(Sql);
        System.out.println("update in support structure status ="+status);

        String Sql1 = "UPDATE electric.distribution_transformer SET estimateno = '"+estimateno+"' WHERE pole_code = '"+poleCode+"'";
        System.out.println(Sql1);
        int status1 = jdbcTemplateObject.update(Sql1);
        System.out.println("Status"+status1);
        return null;

    }

//    public UpdateEstimateModel getPoleData(UpdateEstimateModel model) {
//        String pole_code = model.getPole_code();
//        System.out.println(pole_code);
//        String Sql = "SELECT feeder_code, pole_code, estimateno FROM electric.support_structure WHERE pole_code = '"+pole_code+"'";
//        UpdateEstimateModel poleRecord = jdbcTemplateObject.queryForList(Sql);
//        return null;
//    }

    public UpdateEstimateModel getPoleData(UpdateEstimateModel model) {
        String poleCode = model.getPole_code();
        System.out.println("Pole code: " + poleCode);

        String sql = "SELECT feeder_code, pole_code, estimateno FROM electric.support_structure WHERE pole_code = ?";

        try {
            return jdbcTemplateObject.queryForObject(sql, new Object[]{poleCode}, new RowMapper<UpdateEstimateModel>() {
                @Override
                public UpdateEstimateModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                    UpdateEstimateModel result = new UpdateEstimateModel();
                    result.setCode_of_feeder(rs.getString("feeder_code"));
                    result.setPole_code(rs.getString("pole_code"));
                    result.setEstimateno(rs.getString("estimateno"));
                    return result;
                }
            });
        } catch (EmptyResultDataAccessException e) {
            System.out.println("No record found for pole_code: " + poleCode);
            return null;
        }
    }

//    public List<DistributionCenterMaster> getDcList(String division) {
//        System.out.println(division);
//        String Sql = "SELECT * FROM auth.distribution_center_master WHERE division_code = '3363100'";
//        System.out.println(Sql);
//        List<DistributionCenterMaster> dcList = jdbcTemplateObject.queryForList(Sql);
//        return null;
//    }
public List<DistributionCenterMaster> getDcList(String division) {
//    System.out.println("Division Code: " + division);

    String sql = "SELECT * FROM auth.distribution_center_master WHERE division_code = ?";

    List<DistributionCenterMaster> dcList = jdbcTemplateObject.query(
            sql,
            new Object[]{division},
            new BeanPropertyRowMapper<>(DistributionCenterMaster.class)
    );
//    System.out.println(dcList.toString());
    return dcList;
}

    public List<SupportStructureModel> getDataFfromEstimateno(String estimateno) {

        List<SupportStructureModel> supportStructureModelList = new ArrayList<>();
        String Sql = "select * from electric.support_structure where estimateno = '"+estimateno+"'";
        System.out.println(Sql);
        List<Map<String, Object>> data = jdbcTemplateObject.queryForList(Sql);
        System.out.println("Data length="+data.size());
        if(data.size()>0)
        {
            for(Map row: data)
            {
                System.out.println("DATA = "+data.toString());
                System.out.println(row.toString());
                SupportStructureModel supportStructureModel = new SupportStructureModel();


//                supportStructureModel.getOgc_fid(String.valueOf(data.get("ogc_fid")));
                // Assuming ogc_fid is of type int in your model
                supportStructureModel.setOgc_fid(Integer.parseInt(row.get("ogc_fid").toString()));
//                supportStructureModel.setComments(String.valueOf(row.get("comments"))); //null
//                supportStructureModel.setJobid(String.valueOf(row.get("jobid"))); // null

                //supportStructureModel.setInstallationdate(String.valueOf(row.get("installationdate")));

//                two ways to set date
//                One is
//                Date date = (Date) row.get("installationdate");
//                supportStructureModel.setInstallationdate(date != null ? LocalDate.parse(date.toString()) : null);
//                 Second is
//                supportStructureModel.setInstallationdate(((Date) row.get("installationdate")).toLocalDate());

                supportStructureModel.setSubtypecd((Long) row.get("subtypecd"));;
                supportStructureModel.setSubStationCode(String.valueOf(row.get("sub_station_code")));
                supportStructureModel.setPoleCode(String.valueOf(row.get("pole_code")));
                supportStructureModel.setFeederCode(String.valueOf(row.get("feeder_code")));
                supportStructureModel.setEstimateno(String.valueOf(row.get("estimateno")));
                supportStructureModel.setFeederType(String.valueOf(row.get("feeder_type")));
//                supportStructureModel.setWkbGeometry(row.get("wkb_geometry")); need to convert to geometry
                supportStructureModel.setSubdivisionId(String.valueOf(row.get("subdivision_id")));
                supportStructureModel.setDivisionId(String.valueOf(row.get("division_id")));
                supportStructureModel.setCircleId(String.valueOf(row.get("circle_id")));
                supportStructureModel.setCreatedBy(String.valueOf(row.get("created_by")));

                Date createdDateTime = (Date) row.get("created_datetime");
                supportStructureModel.setCreatedDatetime(createdDateTime != null ? LocalDate.parse(createdDateTime.toString()):null);
//                supportStructureModel.setCreatedDatetime(row.get("created_datetime")); //need to convert local date

                supportStructureModel.setModifiedBy(String.valueOf(row.get("modified_by")));

                Date modifiedDateTime = (Date) row.get("modified_datetime");
                supportStructureModel.setModifiedDatetime(modifiedDateTime !=null ? LocalDate.parse(modifiedDateTime.toString()):null);
//                supportStructureModel.setModifiedDatetime(row.get("modified_datetime"));

                supportStructureModel.setFeederName(String.valueOf(row.get("feeder_name")));
                supportStructureModel.setSurveyType(String.valueOf(row.get("survey_type")));
                supportStructureModel.setPoleTypeSubtypecd((Integer) row.get("pole_type_subtypecd"));
                supportStructureModel.setSupportStructureType((Integer) row.get("support_structure_type"));
                supportStructureModel.setPoleAbSwitch((Integer) row.get("pole_ab_switch"));
                supportStructureModel.setConductorType((Integer) row.get("conductor_type"));
                supportStructureModel.setConductorSize((Integer) row.get("conductor_size"));
                supportStructureModel.setParentPoleCode(String.valueOf(row.get("parent_pole_code")));
                supportStructureModel.setObjectid(String.valueOf(row.get("objectid")));
                supportStructureModel.setPoleNumber((Integer) row.get("pole_number"));
                supportStructureModel.setNumberOfCircuit((Integer) row.get("number_of_circuit"));
                supportStructureModel.setCircleName(String.valueOf(row.get("circle_name")));
                supportStructureModel.setDivisionName(String.valueOf(row.get("division_name")));
                supportStructureModel.setSubdivisionName(String.valueOf(row.get("subdivision_name")));
                supportStructureModel.setSsName(String.valueOf(row.get("ss_name")));
                supportStructureModel.setPoleSequence((Integer) row.get("pole_sequence"));
                supportStructureModel.setDataType(String.valueOf(row.get("data_type")));
                supportStructureModel.setLatitude((BigDecimal) row.get("latitude"));
                supportStructureModel.setLongitude((BigDecimal) row.get("longitude"));
                supportStructureModel.setSupportStructureTypePresent(String.valueOf(row.get("supportStructureTypePresent")));
                supportStructureModel.setPoleTypePresent(String.valueOf(row.get("PoleTypePresent")));
                supportStructureModel.setConductorTypePresent(String.valueOf(row.get("conductor_type_present")));
                supportStructureModel.setConductorSizePresent(String.valueOf(row.get("conductor_size_present")));
                supportStructureModel.setCreatedByUsername(String.valueOf(row.get("created_by_username")));
                supportStructureModel.setPoleAbSwitchPresent(String.valueOf(row.get("pole_ab_switch_present")));
                supportStructureModel.setSchemeType((Integer) row.get("scheme_type"));
                supportStructureModel.setNumberOfCircuitPresent(String.valueOf(row.get("number_of_circuit_present")));
                supportStructureModel.setRegionCode(String.valueOf(row.get("region_code")));
                supportStructureModel.setRegionName(String.valueOf(row.get("region_name")));
                supportStructureModel.setWkbGeometryJson(String.valueOf(row.get("wkb_geometry_json")));
                supportStructureModel.setSubStationCodeBak(String.valueOf(row.get("sub_station_code_bak")));

//                Date actionTimeStamp = (Date) row.get("actiontimestamp");
//                supportStructureModel.setActiontimestamp(actionTimeStamp != null ? LocalDateTime.from(LocalDate.parse(actionTimeStamp.toString())) :null);

                supportStructureModel.setIsDtrPole(isDtrPole(String.valueOf(row.get("pole_code"))));
               supportStructureModelList.add(supportStructureModel);
//                System.out.println(supportStructureModel.toString());
            }
        }
        return supportStructureModelList;
    }

    public String isDtrPole(String pole)
    {
        List<SupportStructureModel> supportStructureModelList = new ArrayList<>();
//        String Sql = "select * from electric.get_dtr_11_04_kv where polecode = '"+pole+"'";
        String Sql = "SELECT * FROM electric.distribution_transformer where pole_code = '"+pole+"'";
        System.out.println(Sql);
        List<Map<String, Object>> data = jdbcTemplateObject.queryForList(Sql);
        System.out.println("Data length="+data.size());
        System.out.println("idDTRPole Method, Polecode="+pole);
        if(data.size()>0)
            return "yes";
        else
            return "no";
    }


    public List<FeederSurveyCountModel> getFeederCountDashboard_33kv(SurveySearchModel feederSearch) {
        List<FeederSurveyCountModel> feederList = new ArrayList<>();
        try {
            String region = feederSearch.getCode_of_region();
            String circle = feederSearch.getCode_of_circle();
            String div = feederSearch.getCode_of_division();
            String dc = feederSearch.getCode_of_distribution_center();
            List<Map<String, Object>> _stats = jdbcTemplateObject.queryForList(" select * from electric.get_33kv_feeder_count_dashboard('" + region + "','" + circle + "','" + div + "','" + dc + "')");
            if (_stats.size() > 0) {
                for (Map row : _stats) {
                    FeederSurveyCountModel fMaster = new FeederSurveyCountModel();
                    fMaster.setKv_33_circle_name(String.valueOf(row.get("kv_33_circle_name")));
                    fMaster.setKv_33_division_name(String.valueOf(row.get("kv_33_division_name")));
                    fMaster.setKv_33_ehv_ss_name(String.valueOf(row.get("kv_33_ehv_ss_name")));
                    fMaster.setKv_33_feeder_code(String.valueOf(row.get("kv_33_feeder_code")));
                    fMaster.setKv_33_feeder_name(String.valueOf(row.get("kv_33_feeder_name")));
//                    double feLength = row.get("kv_33_feeder_length_in_kms") != null ? Double.parseDouble(String.valueOf(row.get("kv_33_feeder_length_in_kms"))) : 0;
//                    DecimalFormat dFormat = new DecimalFormat("##.00");
//                    fMaster.setKv_33_feeder_length_in_kms(String.valueOf(dFormat.format(feLength)));
                    double feLength_existing = row.get("kv_33_feeder_length_in_kms_existing") != null && row.get("kv_33_feeder_length_in_kms_existing") != "" ? Double.parseDouble(String.valueOf(row.get("kv_33_feeder_length_in_kms_existing"))) : 0;
                    double feLength_proposed = row.get("kv_33_feeder_length_in_kms_proposed") != null && row.get("kv_33_feeder_length_in_kms_proposed") != "" ? Double.parseDouble(String.valueOf(row.get("kv_33_feeder_length_in_kms_proposed"))) : 0;
                    DecimalFormat dFormat = new DecimalFormat("##.00");
                    fMaster.setKv_33_feeder_length_in_kms_existing(String.valueOf(dFormat.format(feLength_existing)));
                    fMaster.setKv_33_feeder_length_in_kms_proposed(String.valueOf(dFormat.format(feLength_proposed)));
                    fMaster.setKv_33_p_voltage_regulations_vr(String.valueOf(row.get("kv_33_p_voltage_regulations_vr")));
                    fMaster.setKv_33_line_loss_in_kw(String.valueOf(row.get("kv_33_line_loss_in_kw")));
                    fMaster.setKv_33_line_loss_p(String.valueOf(row.get("kv_33_line_loss_p")));
                    fMaster.setCode_of_circle(String.valueOf(row.get("code_of_circle")));
                    fMaster.setCode_of_distribution_center(String.valueOf(row.get("code_of_distribution_center")));
                    fMaster.setCode_of_division(String.valueOf(row.get("code_of_division")));
                    fMaster.setCode_of_feeder(String.valueOf(row.get("code_of_feeder")));
                    fMaster.setCode_of_region(String.valueOf(row.get("code_of_region")));
                    fMaster.setType_of_feeder(String.valueOf(row.get("type_of_feeder")));
                    fMaster.setConsumer_count(String.valueOf(row.get("consumer_count")));
                    feederList.add(fMaster);
                }
            }
        } catch (TypeMismatchException e) {
            throw new IllegalArgumentException(e);
        } catch (Exception ex) {
            throw ex;
        } finally {
            return feederList;
        }
    }

    public List<feeder_Dtr_Capacity> getDtrCapacityCount_FeederLength(RapdrpLocationModel filter) {
        List<feeder_Dtr_Capacity> dtr_capacityList = new ArrayList<>();
        try {
            String feeder_type = filter.getType_of_feeder();
            String feeder_code = filter.getCode_of_feeder();
            List<Map<String, Object>> _dtr = jdbcTemplateObject.queryForList(" select * from electric.getdtrcapacitycount_feederlength('" +
                    feeder_code + "','" + feeder_type.toString() + "')");
            if (_dtr.size() > 0) {
                for (Map row : _dtr) {
                    feeder_Dtr_Capacity obj = new feeder_Dtr_Capacity();
                    obj.setName((String) row.get("_name_"));
                    obj.setValue((String) row.get("_value_"));
                    dtr_capacityList.add(obj);
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return dtr_capacityList;
    }


    public Object getRapdrp33kvSubstationMapview(RapdrpLocationModel model) {

            String feeder_code = model.getCode_of_feeder();
            String feeder_type = model.getType_of_feeder();
            String Sql = "select * from electric.get_substation_33_kvss('" + feeder_code + "'," + feeder_type.toString() + ")";
            List<Map<String, Object>> _feeders = jdbcTemplateObject.queryForList(Sql);
            return _feeders;
        }

    public Object getRapdrp132kvSubstationMapview(RapdrpLocationModel model) {
        String feeder_code = model.getCode_of_feeder();
        String feeder_type = model.getType_of_feeder();
        String Sql = " select * from electric.get_substation_132_kvss('" + feeder_code + "'," + feeder_type.toString() + ")";
        List<Map<String, Object>> _feeders = jdbcTemplateObject.queryForList(Sql);
        return _feeders;
    }

    public Object getRapdrp33kvHTLineMapview(RapdrpLocationModel model) {
        String region = model.getCode_of_region();
        String circle = model.getCode_of_circle();
        String div = model.getCode_of_division();
        String dc = model.getCode_of_distribution_center();
        String feeder_code = model.getCode_of_feeder();
        String feeder_type = model.getType_of_feeder();
        String Sql = " select * from electric.get_33kvht_electric_line('" + region + "','" + circle + "','" + div + "','" + dc + "','" + feeder_code + "'," + feeder_type.toString() + ")";
        List<Map<String, Object>> _feeders = jdbcTemplateObject.queryForList(Sql);
        return _feeders;
    }

    public Object getRapdrpPole33kvMapview(RapdrpLocationModel model) {
        String region = model.getCode_of_region();
        String circle = model.getCode_of_circle();
        String div = model.getCode_of_division();
        String dc = model.getCode_of_distribution_center();
        String feeder_code = model.getCode_of_feeder();
        String feeder_type = model.getType_of_feeder();
        String Sql = " select * from electric.get_pole_33_kv('" + region + "','" + circle + "','" + div + "','" + dc + "','" + feeder_code + "'," + feeder_type.toString() + ")";
        System.out.println(Sql);
        List<Map<String, Object>> _feeders = jdbcTemplateObject.queryForList(Sql);
        return _feeders;
    }

    public Object getRapdrpConsumerMapview(RapdrpLocationModel model) {
        String region = model.getCode_of_region();
        String circle = model.getCode_of_circle();
        String div = model.getCode_of_division();
        String dc = model.getCode_of_distribution_center();
        String feeder_code = model.getCode_of_feeder();
        String feeder_type = model.getType_of_feeder();
        String Sql = " select * from electric.get_consumer_in_map('" + region + "','" + circle + "','" + div + "','" + dc + "','" + feeder_code + "'," + feeder_type.toString() + ")";
        List<Map<String, Object>> _feeders = jdbcTemplateObject.queryForList(Sql);
        return _feeders;
    }

    public List<DashboardSummary_33kvModel> getSS_CapacityPTRList(String code_of_feeder) {
        List<DashboardSummary_33kvModel> objlist = new ArrayList<>();
        try {
            List<Map<String, Object>> _dash = jdbcTemplateObject.queryForList("select distinct sub_station_name,coalesce(capacity_ptr,0) from electric.electric_station s\n" +
                    "\t\tinner join electric.feeder_master f on s.sub_station_code=f.code_of_33_11_kv_substation\n" +
                    "where code_of_33_kv_feeder ='" + code_of_feeder + "' and wkb_geometry is not null union\n" +
                    "select distinct sub_station_name,coalesce(capacity_ptr,0) from electric.electric_station s\n" +
                    "\t\tinner join electric.feeder_master f on s.sub_station_code=f.code_of_ehv_substation\n" +
                    "where code_of_33_kv_feeder ='" + code_of_feeder + "' and wkb_geometry is not null");
            if (_dash.size() > 0) {
                for (Map row : _dash) {
                    DashboardSummary_33kvModel obj = new DashboardSummary_33kvModel();
                    obj.setSs_name(String.valueOf(row.get("sub_station_name")));
                    obj.setCapacity_ptr(row.get("capacity_ptr") == null ? "0" : String.valueOf(row.get("capacity_ptr")));
                    objlist.add(obj);
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return objlist;
    }

    public List<Map<String, Object>> findByFeederCode(String feederCode) {
        String Sql = "select * from electric.distribution_transformer where feeder_code = '"+feederCode+"'";
        System.out.println(Sql);
        List<Map<String, Object>> data = jdbcTemplateObject.queryForList(Sql);
        System.out.println("Data length="+data.size());
        return data;

    }

    public List<Map<String, Object>> getDtrMapView(String dtrUniqueCode) {
        String Sql = "SELECT * FROM electric.support_structure where feeder_code ='"+dtrUniqueCode+"'";
        List<Map<String, Object>> data = jdbcTemplateObject.queryForList(Sql);
        System.out.println("Data length ="+data.size());
        return data;
    }

    public Object getLTkvLineMapview(RapdrpLocationModel model) {
        String feeder_code = model.getCode_of_feeder();
        String lt_feeder_code = model.getLt_feeder_code();
        String Sql = " select * from electric.get_ltkv_electric_line('" + feeder_code + "','" + lt_feeder_code + "')";
        List<Map<String, Object>> _feeders = jdbcTemplateObject.queryForList(Sql);
        return _feeders;
    }
}


